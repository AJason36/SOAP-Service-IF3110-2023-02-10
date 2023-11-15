package com.soap.controllers;

import com.soap.dao.SubscriptionDao;
import com.soap.exceptions.DaoException;
import com.soap.models.Subscription;
import com.soap.models.builders.SubscriptionBuilder;

public class SubscriptionController {
    private SubscriptionDao dao = new SubscriptionDao();
    private SubscriptionBuilder builder = new SubscriptionBuilder();

    /**
     * Get subscriptions of a user
     * @param username
     * @return subscriptions of a user
     * @throws DaoException
     */
    public Subscription[] getSubscriptionsOf(String username) throws DaoException {
        Subscription args = builder.setSubscriber(username).create();

        try {
            return dao.findSubscription(args);
        } catch (DaoException e) {
            throw e;
        }
    }
}
