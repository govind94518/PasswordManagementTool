package com.epam.exception;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException(String errorMessage) {
        super(errorMessage);

    }
}
