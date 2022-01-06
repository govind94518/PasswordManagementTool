package com.epam.exception;

public class NoGroupFoundForAccount extends Exception {
    public NoGroupFoundForAccount(String errorMessage) {
        super(errorMessage);
    }
}
