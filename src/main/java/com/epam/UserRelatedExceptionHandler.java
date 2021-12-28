package com.epam;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserRelatedExceptionHandler {

    public static final String ERROR = "error";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value= NoGroupFoundForAccount.class)
    public  String  noGroupFoundForAccountHandler(Model m){
        m.addAttribute("msg","Oops! No group found!!");
        return ERROR;
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value= AccountAlreadyExistsException.class)
    public  String  noAccountAlreadyExistsException(Model m){
        m.addAttribute("msg","Oops! Account already exists!!");
        return ERROR;
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value=  NoRecordFoundForGroup.class)
    public  String  noNoRecordFoundForGroup(Model m){
        m.addAttribute("msg","Oops! No Record found for group");
        return ERROR;
    }
}
