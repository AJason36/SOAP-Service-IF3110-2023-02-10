package com.soap.middlewares;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;


public class ApiKeyHandler implements SOAPHandler<SOAPMessageContext>{
    private final String[] SERVICE_CLIENTS = {
        "php_service",
        "rest_service"
    };

    private final Map<String, String> API_KEY_OF;

    public ApiKeyHandler() {
        super();
        API_KEY_OF = new HashMap<String, String>();
        for (int i = 0; i < SERVICE_CLIENTS.length; i++) {
            API_KEY_OF.put(
                SERVICE_CLIENTS[i], 
                System.getenv(SERVICE_CLIENTS[i].toUpperCase() + "_API_KEY")
            );
        }
    }

    @Resource
    private WebServiceContext webServiceContext;

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (isOutbound) {
            return true;
        }

        String receivedApiKey = extractApiKeyFromHeader(context);
        if (receivedApiKey == null) {
            SOAPFaultException soapFaultException = generateSoapFaultException("API key is missing");
            throw soapFaultException;
        }
        
        if (!isValidApiKey(receivedApiKey)) {
            SOAPFaultException soapFaultException = generateSoapFaultException("Invalid API key");
            throw soapFaultException;
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {}

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    private boolean isValidApiKey(String apiKey) {
        return API_KEY_OF.containsValue(apiKey);
    }

    private String extractApiKeyFromHeader(SOAPMessageContext context) {
        try {
            String apiKey = (String) context.getMessage().getSOAPHeader().getElementsByTagName("ApiKey")
            .item(0).getChildNodes().item(0).getNodeValue();
            return apiKey;
        } catch (Exception e) {
            return null;
        }
    }

    private SOAPFaultException generateSoapFaultException(String message) {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault soapFault = soapFactory.createFault(message, new QName("Client"));
            return new SOAPFaultException(soapFault);
        } catch (Exception e) {
            return null;
        }
    }
}
