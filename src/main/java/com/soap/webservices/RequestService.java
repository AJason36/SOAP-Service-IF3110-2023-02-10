package com.soap.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.soap.controllers.RequestController;
import com.soap.exceptions.DaoException;
import com.soap.models.Response;
import com.soap.models.ResponseCode;
import com.soap.models.SubRequest;
import com.soap.models.Subscription;

@WebService
public class RequestService {
    private RequestController reqController = new RequestController();

    @WebMethod
    public Response<SubRequest> MakeRequest(String requestBy, String to, String requesterEmail) {
        try {
            // Make request
            SubRequest request = reqController.makeRequest(requestBy, to, requesterEmail);
            
            // Make response
            String message = "Request created successfully";
            Response<SubRequest> response = new Response<SubRequest>(
                ResponseCode.SUCCESS, message, request
            );
            
            // Return response
            return response;
        } catch (DaoException e) {
            return new Response<SubRequest>(
                e.getCode(), e.getMessage(), null
            );
        }
    }

    @WebMethod
    public Response<Subscription> ApproveRequest(String requestBy, String to) {
        try {
            // Approve request
            Subscription createdSubscription = reqController.approveRequest(requestBy, to);
            
            // Make response
            String message = "Request created successfully";
            Response<Subscription> response = new Response<Subscription>(
                ResponseCode.SUCCESS, message, createdSubscription
            );
            
            // Return response
            return response;
        } catch (DaoException e) {
            return new Response<Subscription>(
                e.getCode(), e.getMessage(), null
            );
        }
    }

    @WebMethod
    public Response<SubRequest> RejectRequest(String requestBy, String to) {
        try {
            // Reject request
            SubRequest rejectedRequest = reqController.rejectRequest(requestBy, to);
            
            // Make response
            String message = "Request rejected successfully";
            Response<SubRequest> response = new Response<SubRequest>(
                ResponseCode.SUCCESS, message, rejectedRequest
            );
            
            // Return response
            return response;
        } catch (DaoException e) {
            return new Response<SubRequest>(
                e.getCode(), e.getMessage(), null
            );
        }
    }

    @WebMethod
    public Response<SubRequest[]> GetRequestsOf(String requestee) {
        try {
            // Get requests
            SubRequest[] requests = reqController.getRequestsOf(requestee);
            
            // Make response
            String message = "Found " + requests.length + " requests"; 
            Response<SubRequest[]> response = new Response<SubRequest[]>(
                ResponseCode.SUCCESS, message, requests
            );
            
            // Return response
            return response;
        } catch (DaoException e) {
            return new Response<SubRequest[]>(
                e.getCode(), e.getMessage(), null
            );
        }
    }
}
