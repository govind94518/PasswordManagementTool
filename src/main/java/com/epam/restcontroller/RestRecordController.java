package com.epam.restcontroller;
import com.epam.dto.RegisterDTO;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/record")
public class RestRecordController {
    @Autowired
    AccountService accountService;

    @GetMapping("registerAccount")
    public ResponseEntity<String> registerAccount() {
        String status = "register page success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @PostMapping("createAccount")
    public ResponseEntity<String> createAccount(@Valid @RequestBody RegisterDTO register) throws AccountAlreadyExistsException, NoGroupFoundForAccount {
        boolean isResult = accountService.registerAccount(register);
        String result = "record already exist";
        HttpStatus statusCode = HttpStatus.ALREADY_REPORTED;
        if (isResult) {
            result = "record saved";
            statusCode = HttpStatus.CREATED;
        }
        return new ResponseEntity<String>(result, statusCode);
    }
}
