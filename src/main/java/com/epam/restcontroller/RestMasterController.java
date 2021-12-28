package com.epam.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
public class RestMasterController {

    @GetMapping("/")
    public ResponseEntity<String> menuForm() {
        String status = "menu page success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("login")
    public ResponseEntity<String> masterLoginForm() {
        String status = "master page success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("saveMaster/{administratorName}/{administratorPassword}")
    public ResponseEntity<String> checkMasterInformation(@PathVariable String administratorName, @PathVariable String administratorPassword) {
        String result = "";
        String admin = "admin";
        String password = "12345";
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        if (administratorName.equals(admin) && administratorPassword.equals(password)) {
            result = "success";
            statusCode = HttpStatus.ACCEPTED;
        }
        if (result.isEmpty()) result = "error";
        return new ResponseEntity<String>(result, statusCode);
    }
}

