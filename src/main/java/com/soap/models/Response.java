package com.soap.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    static public final int SUCCESS = 200;
    static public final int SERVER_ERROR = 500;
    static public final int BAD_REQUEST = 400;
    static public final int NOT_FOUND = 404;

    private int code;
    private String message;
    private T data;
}
