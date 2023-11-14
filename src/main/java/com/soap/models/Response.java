package com.soap.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private ResponseCode code;
    private String message;
    private T data;
}
