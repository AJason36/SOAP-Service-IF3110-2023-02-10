package com.soap.exceptions;

import com.soap.models.ResponseCode;

import lombok.Getter;

@Getter
public class DaoException extends Exception{
    private int code;

    public DaoException(String message) {
        super(message);
        this.code = ResponseCode.SERVER_ERROR;
    }

    public DaoException(String message, int code) {
        super(message);
        this.code = code;
    }
}
