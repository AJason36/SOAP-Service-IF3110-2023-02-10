package com.soap.models;

import java.sql.Timestamp;

public class SubRequestBuilder {
    private String requester; // refer to PHP service username @PK
    private String requestee; // refer to REST JS service username @PK
    private String requesterEmail;
    private Timestamp createdAt;

    public SubRequestBuilder setRequester(String requester) {
        this.requester = requester;
        return this;
    }

    public SubRequestBuilder setRequestee(String requestee) {
        this.requestee = requestee;
        return this;
    }

    public SubRequestBuilder setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
        return this;
    }

    public SubRequestBuilder setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SubRequestBuilder setAllArgs(String requester, String requestee, String requesterEmail, Timestamp createdAt) {
        this.requester = requester;
        this.requestee = requestee;
        this.requesterEmail = requesterEmail;
        this.createdAt = createdAt;
        return this;
    }

    public SubRequest create() {
        SubRequest subRequest = new SubRequest(requester, requestee, requesterEmail, createdAt);
        reset();
        return subRequest;
    }

    private void reset() {
        this.requester = null;
        this.requestee = null;
        this.requesterEmail = null;
        this.createdAt = null;
    }
}
