package com.griddynamics.user.exception;

public class AddressException extends BaseException {
    public AddressException(String message) {
        super(message, 400);
    }
}
