package com.soap.middlewares;

import java.util.Set;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.soap.util.LoggingUtils;
import com.soap.util.SOAPContextUtils;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
    @Resource
    private WebServiceContext webServiceContext;

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        if (isOutbound) {
            return true;
        }

        String operation = context.get(MessageContext.WSDL_OPERATION).toString();
        operation = operation.replace("{", "").replace("}", "");
        String args = SOAPContextUtils.extractArgs(context);

        String description = "Called " + operation + " with arguments: " + args;
        System.out.println("description: " + description);

        String ipAddress = SOAPContextUtils.extractIPAddress(context);
        String endpoint = SOAPContextUtils.extractURLEndpoint(context);
        
        System.out.println("ipAddress: " + ipAddress);
        System.out.println("endpoint: " + endpoint);

        LoggingUtils.log(description, ipAddress, endpoint);

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
}
