package com.soap;

import javax.xml.ws.Endpoint;

import com.soap.ws.RequestService;
import com.soap.ws.SubscriptionService;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8080/debug", new DebugService());
            Endpoint.publish("http://localhost:8080/request", new RequestService());
            Endpoint.publish("http://localhost:8080/subscription", new SubscriptionService());
            System.out.println("Starting server...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}