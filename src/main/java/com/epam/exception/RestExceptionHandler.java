//package com.epam.exception;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@ControllerAdvice
//public class RestExceptionHandler {
//
//    @ExceptionHandler(value= NoGroupFoundForAccount.class)
//    public Map<String, String> handlesNoGroupFoundForAccountException( NoGroupFoundForAccount  noGroupFoundForAccount) {
//        Map<String, String> response = new HashMap<>();
//        response.put("timestamp", new Date().toString());
//        response.put("error",  noGroupFoundForAccount.getMessage());
//        response.put("status", HttpStatus.NOT_FOUND.name());
//        return response;
//    }
//    @ExceptionHandler(value= AccountAlreadyExistsException.class)
//    public Map<String, String> handlesAccountAlreadyExistsException( AccountAlreadyExistsException  accountAlreadyExistsException) {
//        Map<String, String> response = new HashMap<>();
//        response.put("timestamp", new Date().toString());
//        response.put("error",  accountAlreadyExistsException.getMessage());
//        response.put("status", HttpStatus.NOT_FOUND.name());
//        return response;
//    }
//    @ExceptionHandler(value=  NoRecordFoundForGroup.class)
//    public Map<String, String> handlesNoRecordFoundForGroupException( NoRecordFoundForGroup noRecordFoundForGroup) {
//        Map<String, String> response = new HashMap<>();
//        response.put("timestamp", new Date().toString());
//        response.put("error",  noRecordFoundForGroup.getMessage());
//        response.put("status", HttpStatus.NOT_FOUND.name());
//        return response;
//    }
//
//}
