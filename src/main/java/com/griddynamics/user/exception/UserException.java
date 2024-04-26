package com.griddynamics.user.exception;

public class UserException extends BaseException {
    public UserException(String message) {
        super(message, 400);
    }
}
