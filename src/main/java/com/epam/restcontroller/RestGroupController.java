package com.epam.restcontroller;

import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import com.epam.service.AccountService;
import com.epam.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/group")
public class RestGroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    AccountService accountService;

    @GetMapping("listofgroups")
    public List<Group> displayListOfGroups() throws NoGroupFoundForAccount {
        List<Group> groups = groupService.findAllGroups();
        return groups;
    }

    @GetMapping("inputGroupName")
    public ResponseEntity<String> inputGroupName() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("displayAllRecordsByGroupName/{groupName}")
    public List<Account> displayAllRecordByGroupName(@PathVariable String groupName) throws NoRecordFoundForGroup {
        List<Account> accounts = groupService.findAllRecordByGroupName(groupName);
        return accounts;
    }

    @GetMapping("inputUpdateGroupName")
    public ResponseEntity<String> inputUpdateGroupName() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @PutMapping("updateGroupName/{oldGroupName}/{newGroupName}")
    public ResponseEntity<String> updateGroupName(@PathVariable String oldGroupName, @PathVariable String newGroupName) throws NoGroupFoundForAccount {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String result = "error";
        if (groupService.updateGroup(newGroupName, oldGroupName)) {
            result = "success";
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<String>(result, statusCode);
    }

    @GetMapping("inputDeleteGroupName")
    public ResponseEntity<String> takeDeleteGroupName() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @DeleteMapping("deleteGroupName/{groupName}")
    public ResponseEntity<String> deleteGroupName(@PathVariable String groupName) throws NoGroupFoundForAccount, NoRecordFoundForGroup {
        boolean isUpdated = groupService.deleteGroup(groupName);
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String status = "error";
        if (isUpdated) {
            status = "success";
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("inputDeleteUserAccount")
    public ResponseEntity<String> inputDeleteUserAccount() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @DeleteMapping("deleteUserAccount/{userAccountName}/{groupName}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable String userAccountName, @PathVariable String groupName) throws NoGroupFoundForAccount, NoRecordFoundForGroup {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String status = "error";
        boolean isAccountDeleted = accountService.deleteAccountFromGroup(userAccountName, groupName);
        if (isAccountDeleted) {
            status = "success";
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("inputUpdatePasswordFromAccount")
    public ResponseEntity<String> inputUpdatePasswordFromAccount() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @PutMapping("updatePasswordFromAccount/{userName}/{groupName}/{newPassword}")
    public ResponseEntity<String> updatePasswordFromAccount(@PathVariable String userName, @PathVariable String groupName, @PathVariable String newPassword) throws NoGroupFoundForAccount {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String status = "error";
        boolean isPasswordUpdated = accountService.updatePasswordFromUserAccount(groupName, userName, newPassword);
        if (isPasswordUpdated) {
            status = "success";
            statusCode = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("inputRetrievalPasswordFromUserAccount")
    public ResponseEntity<String> inputRetrievalPasswordFromUserAccount() {
        String status = " success";
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        return new ResponseEntity<String>(status, statusCode);
    }

    @GetMapping("retrievalPasswordFromUserAccount/{userAccountName}/{groupName}")
    public ResponseEntity<String> retrievalPasswordFromUserAccount(String userAccountName, String groupName) throws NoGroupFoundForAccount, NoRecordFoundForGroup {
        HttpStatus statusCode = HttpStatus.ACCEPTED;
        String password = accountService.retrievalPassword(userAccountName, groupName);
        if (password == null) {
            statusCode = HttpStatus.NOT_FOUND;
            password = "error";
        }
        return new ResponseEntity<String>(password, statusCode);
    }
}
