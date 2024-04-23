package com.griddynamics.user.exceptions;

public class UserException extends BaseException {
    public UserException(String message) {
        super(message, 400);
    }
}
