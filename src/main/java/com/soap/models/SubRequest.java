package com.soap.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class SubRequest {
    private String requester; // refer to PHP service username @PK
    private String requestee; // refer to REST JS service username @PK
    private String requesterEmail;
    private XMLGregorianCalendar createdAt;
}
