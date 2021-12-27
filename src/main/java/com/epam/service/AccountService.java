package com.epam.service;

import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;

import com.epam.entity.Account;
import com.epam.exception.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {

    public boolean registerAccount(RegisterDTO registerDTO) throws   AccountAlreadyExistsException ,NoGroupFoundForAccount;

    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistException ;
    public  boolean accountPresent(String userName,String groupName)throws NoGroupFoundForAccount;
    public List<Account> allAccountByGroupName(String  groupName)throws NoGroupFoundForAccount;
    public    boolean deleteAccountFromGroup(String userName,String groupName)throws  NoGroupFoundForAccount;
    public   boolean updatePasswordFromUserAccount(String groupName, String userName, String newPassword)throws  NoGroupFoundForAccount;
    public   String retrievalPassword(String userName, String groupName)throws  NoGroupFoundForAccount;

}
