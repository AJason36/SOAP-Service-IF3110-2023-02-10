package com.soap.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subscription {
    private String subscriber; // refer to PHP service username @PK
    private String curator;  // refer to REST JS service username @PK
    private Timestamp approvedAt;
    private Boolean isActive;
    private Timestamp validUntil;
}
