package com.epam.exception;

public class GroupShouldNotContainsRecords extends Exception {
    public GroupShouldNotContainsRecords(){
        super("Oops! Group should be empty before deletion!!");
    }
}
