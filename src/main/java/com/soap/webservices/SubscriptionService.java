package com.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.soap.models.Response;
import com.soap.models.Subscription;

@WebService
public class SubscriptionService {
    @WebMethod
    public Response<Subscription[]> GetSubscription() {
        return null;
    }
}
