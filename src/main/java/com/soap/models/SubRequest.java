package com.soap.models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubRequest {
    private String requester; // refer to PHP service username @PK
    private String requestee; // refer to REST JS service username @PK
    private String requesterEmail;
    private Timestamp createdAt;


}
