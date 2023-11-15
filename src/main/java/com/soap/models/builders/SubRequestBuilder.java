package com.soap.models.builders;

import java.sql.Timestamp;

import javax.xml.datatype.XMLGregorianCalendar;

import com.soap.models.SubRequest;
import com.soap.util.DbUtils;

public class SubRequestBuilder {
    private String requester; // refer to PHP service username @PK
    private String requestee; // refer to REST JS service username @PK
    private String requesterEmail;
    private XMLGregorianCalendar createdAt;

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
        this.createdAt = DbUtils.timestampToGregorianXML(createdAt);;
        return this;
    }

    public SubRequestBuilder setAllArgs(String requester, String requestee, String requesterEmail, Timestamp createdAt) {
        this.requester = requester;
        this.requestee = requestee;
        this.requesterEmail = requesterEmail;
        this.createdAt = DbUtils.timestampToGregorianXML(createdAt);
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
