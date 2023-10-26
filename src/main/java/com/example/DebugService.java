package com.example;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class DebugService {
    @WebMethod
    public String Greet(String name) {
        return "Welcome " + name + "!";
    }
}
