package com.soap.models;

public enum ResponseCode {
    SUCCESS (200),
    SERVER_ERROR (500),
    BAD_REQUEST (400),
    NOT_FOUND (404);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
