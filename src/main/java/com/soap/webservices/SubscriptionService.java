package com.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.soap.controllers.SubscriptionController;
import com.soap.exceptions.DaoException;
import com.soap.models.Response;
import com.soap.models.ResponseCode;
import com.soap.models.Subscription;

@WebService
public class SubscriptionService {

    private SubscriptionController subController = new SubscriptionController();

    @WebMethod
    public Response<Subscription[]> GetSubscriptionOf(String username) {
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
