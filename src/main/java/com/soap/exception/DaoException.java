package com.soap.exception;

public class DaoException extends Exception {
    public int code;
    
    public DaoException(String message, int code) {
        super(message);
        this.code = code;
    }

    public DaoException(String message) {
        this(message, 400);
    }
}
