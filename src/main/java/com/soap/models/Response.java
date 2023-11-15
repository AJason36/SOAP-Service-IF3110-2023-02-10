package com.soap.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@XmlRootElement
@XmlSeeAlso({SubRequest.class, Subscription.class, SubRequest[].class, Subscription[].class})
public class Response<T> {
    public Response() {
        this.code = ResponseCode.SERVER_ERROR;
        this.message = "Unknown error";
        this.data = null;

        System.out.println(this.toString());
    }

    @XmlElement
    private int code;

    @XmlElement
    private String message;
    
    @XmlElement
    private T data;


    public String toString() {
        return "Response: " + this.code + " " + this.message + " " + this.data;
    }
}
