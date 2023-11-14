package com.soap.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubRequest {
    private int requestId;
    private String requester; // refer to PHP service username
    private String requesterEmail;
    private Timestamp createdAt;
    private String requestee; // refer to REST JS service username

    
}
