package com.soap.controllers;

import com.soap.dao.SubscriptionDao;
import com.soap.models.Subscription;

public class SubscriptionController {
    private SubscriptionDao dao = new SubscriptionDao();

    public Subscription[] getSubscriptionsOf(String username) {
        // Subscription

        // return dao.findSubscription(username);
        return null;
    }
}
