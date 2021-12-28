package com.epam.exception;

public class RecordAlreadyExistsException extends Exception {
    public RecordAlreadyExistsException() {
        super("Oops! Record already exists for url!!");
    }
}
