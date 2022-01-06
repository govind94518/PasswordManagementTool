package com.epam.exception;

public class NoRecordFoundForGroup extends Exception{
    public NoRecordFoundForGroup(String errorMessage) {
        super(errorMessage);
    }
}
