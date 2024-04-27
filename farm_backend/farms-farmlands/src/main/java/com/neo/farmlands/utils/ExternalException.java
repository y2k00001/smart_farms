package com.neo.farmlands.utils;

import lombok.Data;

@Data
public class ExternalException extends RuntimeException {
    private String code;

    public ExternalException(String code, String message) {
        super(message);
        this.code = code;
    }
}
