package com.griddynamics.user.exceptions;

public class AddressException extends BaseException {
    public AddressException(String message) {
        super(message, 400);
    }
}
