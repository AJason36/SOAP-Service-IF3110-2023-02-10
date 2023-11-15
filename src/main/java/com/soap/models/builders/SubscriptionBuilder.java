package com.soap.models.builders;

import java.sql.Timestamp;

import javax.xml.datatype.XMLGregorianCalendar;

import com.soap.models.Subscription;
import com.soap.util.DbUtils;

public class SubscriptionBuilder {
    private String subscriber; // refer to PHP service username @PK
    private String curator;  // refer to REST JS service username @PK
    private XMLGregorianCalendar approvedAt;
    private Boolean isActive;
    private XMLGregorianCalendar validUntil;

    public SubscriptionBuilder setSubscriber(String subscriber) {
        this.subscriber = subscriber;
        return this;
    }

    public SubscriptionBuilder setCurator(String curator) {
        this.curator = curator;
        return this;
    }

    public SubscriptionBuilder setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public SubscriptionBuilder setApprovedAt(Timestamp approvedAt) {
        this.approvedAt = DbUtils.timestampToGregorianXML(approvedAt);
        return this;
    }

    public SubscriptionBuilder setValidUntil(Timestamp validUntil) {
        this.validUntil = DbUtils.timestampToGregorianXML(validUntil);
        return this;
    }

    public SubscriptionBuilder setAllArgs(String subscriber, String curator, Boolean isActive, Timestamp approvedAt, Timestamp validUntil) {
        this.subscriber = subscriber;
        this.curator = curator;
        this.isActive = isActive;
        this.approvedAt = DbUtils.timestampToGregorianXML(approvedAt);
        this.validUntil = DbUtils.timestampToGregorianXML(validUntil);
        return this;
    }

    public Subscription create() {
        Subscription subscription = new Subscription(subscriber, curator, approvedAt, isActive, validUntil);
        reset();
        return subscription;
    }

    private void reset() {
        this.subscriber = null;
        this.curator = null;
        this.isActive = null;
        this.approvedAt = null;
        this.validUntil = null;
    }
}
