package com.griddynamics.user.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final int statusCode;

    public BaseException(final String message, final int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
