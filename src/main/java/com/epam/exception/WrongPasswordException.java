package com.epam.exception;

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Oops! Password does not matched!!");
    }
}
