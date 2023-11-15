package com.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.soap.models.Response;
import com.soap.models.ResponseCode;
import com.soap.models.SubRequest;
import com.soap.models.Subscription;

@WebService
public class RequestService {
    @WebMethod
    public Response<SubRequest> MakeRequest(String requestBy, String to) {
        return new Response<SubRequest>(ResponseCode.SUCCESS, "Success", null);
    }

    @WebMethod
    public Response<Subscription> ApproveRequest(String requestBy, String to) {
        return new Response<Subscription>(ResponseCode.SUCCESS, "Success", null);
    }

    @WebMethod
    public Response<SubRequest> RejectRequest(String requestBy, String to) {
        return new Response<SubRequest>(ResponseCode.SUCCESS, "Success", null);
    }

    @WebMethod
    public Response<SubRequest[]> GetRequests(String requestee) {
        return new Response<SubRequest[]>(ResponseCode.SUCCESS, "Success", null);
    }
}
