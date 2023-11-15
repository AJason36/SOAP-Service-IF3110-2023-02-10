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
public class Subscription {
    private String subscriber; // refer to PHP service username @PK
    private String curator;  // refer to REST JS service username @PK
    private XMLGregorianCalendar approvedAt;
    private Boolean isActive;
    private XMLGregorianCalendar validUntil;
}
