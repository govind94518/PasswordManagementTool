package com.epam.exception;

public class NoRecordFoundForAccount extends Exception {
    public NoRecordFoundForAccount(){
        super("Oops! No Record Found!!");
    }
}
