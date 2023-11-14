package com.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.soap.models.Response;
import com.soap.models.SubRequest;
import com.soap.models.Subscription;

@WebService
public class SubscriptionService {
    @WebMethod
    public Response<SubRequest> MakeRequest() {
        return new Response<SubRequest>(Response.SUCCESS, "Success", null);
    }

    @WebMethod
    public Response<Subscription> ApproveRequest() {
        return new Response<Subscription>(Response.SUCCESS, "Success", null);
    }

    @WebMethod
    public Response<SubRequest> RejectRequest() {
        return new Response<SubRequest>(Response.SUCCESS, "Success", null);
    }
}
