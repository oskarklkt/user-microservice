package com.griddynamics.user.exception;


public class NoSuchElementException extends BaseException {
    public NoSuchElementException(String message) {
        super(message, 404);
    }
}