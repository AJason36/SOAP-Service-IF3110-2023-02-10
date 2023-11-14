package com.soap.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subscription {
    private int subscriptionId;
    private String subscriber; // refer to PHP service username
    private String curator;  // refer to REST JS service username
    private Timestamp approvedAt;
    private Boolean isActive;
    private Timestamp validUntil;
}
