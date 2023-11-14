package com.soap.exceptions;

import javax.xml.ws.WebFault;

@WebFault
public class DaoException extends Exception{
    public DaoException(String message) {
        super(message);
    }
}
