package com.soap.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Log {
    private String description;
    private String ipAddress;
    private String endpoint;
}
