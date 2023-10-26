package com.soap;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8080/debug", new DebugService());
            System.out.println("Starting server...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}