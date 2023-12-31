package com.soap.ws;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import com.soap.controllers.SubscriptionController;
import com.soap.exceptions.DaoException;
import com.soap.models.Response;
import com.soap.models.ResponseCode;
import com.soap.models.Subscription;

@WebService
@HandlerChain(file = "handler-chain.xml")
public class SubscriptionService {

    private SubscriptionController subController = new SubscriptionController();

    @WebMethod
    @WebResult(name = "Response")
    public Response<Subscription[]> GetSubscriptionOf(
        @WebParam(name = "ApiKey", header = true) Holder<String> apiKey,
        @WebParam(name = "Username") String username
    ) {
        try {
            // Get subscriptions
            Subscription[] subscriptions = subController.getSubscriptionsOf(username);
            
            // Make response
            String message = "Found " + subscriptions.length + " subscriptions"; 
            Response<Subscription[]> response = new Response<Subscription[]>(
                ResponseCode.SUCCESS, message, subscriptions
            );
            
            // Return response
            return response;
        } catch (DaoException e) {
            return new Response<Subscription[]>(
                e.getCode(), e.getMessage(), null
            );
        }
    }
}
