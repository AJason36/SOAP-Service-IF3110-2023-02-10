package com.soap.controllers;

import com.soap.dao.RequestDao;
import com.soap.dao.SubscriptionDao;
import com.soap.exceptions.DaoException;
import com.soap.models.SubRequest;
import com.soap.models.Subscription;
import com.soap.models.builders.SubRequestBuilder;
import com.soap.models.builders.SubscriptionBuilder;
import com.soap.util.DbUtils;

public class RequestController {
    private SubscriptionDao subDao = new SubscriptionDao();
    private RequestDao requestDao = new RequestDao();

    private SubscriptionBuilder subBuilder = new SubscriptionBuilder();
    private SubRequestBuilder requestBuilder = new SubRequestBuilder();

    /**
     * Create a request for subscription
     * @param requester Who requests the subscription
     * @param requestee Who is requested for subscription
     * @param requesterEmail Email of requester
     * @return created request
     * @throws DaoException
     */
    public SubRequest makeRequest(String requester, String requestee, String requesterEmail) throws DaoException {
        SubRequest request = requestBuilder.setRequester(requester).setRequestee(requestee)
                .setRequesterEmail(requesterEmail).create();

        try {
            requestDao.createRequest(request);
            return request;
        } catch (DaoException e) {
            throw e;
        }
    }

    /**
     * Get list of request of a curator
     * @param requestee Who is requested for subscription
     * @return list of request of a curator
     * @throws DaoException
     */
    public SubRequest[] getRequestsOf(String requestee) throws DaoException {
        SubRequest args = requestBuilder.setRequestee(requestee).create();

        try {
            return requestDao.findRequest(args);
        } catch (DaoException e) {
            throw e;
        }
    }

    /**
     * Approve a request
     * @param requester Who requests the subscription
     * @param requestee Who is requested for subscription
     * @return subscription of approved request
     * @throws DaoException
     */
    public Subscription approveRequest(String requester, String requestee) throws DaoException {
        SubRequest request = requestBuilder.setRequester(requester).setRequestee(requestee).create();
        Subscription sub = subBuilder.setSubscriber(requester).setCurator(requestee).create();

        try {
            DbUtils.beginTransaction();

            requestDao.deleteRequest(request);
            subDao.createSubscription(sub);

            DbUtils.commitTransaction();
            return sub;
        } catch (DaoException e) {
            DbUtils.rollbackTransaction();
            throw e;
        }
    }

    /**
     * Reject a request
     * @param requester Who requests the subscription
     * @param requestee Who is requested for subscription
     * @return rejected request
     * @throws DaoException
     */
    public SubRequest rejectRequest(String requester, String requestee) throws DaoException {
        SubRequest request = requestBuilder.setRequester(requester).setRequestee(requestee).create();

        try {
            requestDao.deleteRequest(request);
            return request;
        } catch (DaoException e) {
            throw e;
        }
    }
}
