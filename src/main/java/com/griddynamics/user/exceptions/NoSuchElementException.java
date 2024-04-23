package com.griddynamics.user.exceptions;


public class NoSuchElementException extends BaseException {
    public NoSuchElementException(String message) {
        super(message, 404);
    }
}