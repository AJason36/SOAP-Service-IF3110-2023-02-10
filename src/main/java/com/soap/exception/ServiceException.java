package com.soap.exception;
import javax.xml.ws.WebFault;

@WebFault(name = "ServiceException")
public class ServiceException extends Exception{
    public int code = 400;
    
    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        this(400, message);
    }
}
