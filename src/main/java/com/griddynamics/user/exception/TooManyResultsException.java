package com.griddynamics.user.exception;

public class TooManyResultsException extends RuntimeException {
    public TooManyResultsException(String message) {
        super(message);
    }
}
