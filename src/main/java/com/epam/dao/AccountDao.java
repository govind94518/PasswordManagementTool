package com.epam.dao;

import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.AccountDoesNotExistException;
import com.epam.exception.NoGroupFoundForAccount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountDao {
    public   boolean registerAccount(RegisterDTO registerDTO) throws AccountAlreadyExistsException, NoGroupFoundForAccount;
    public   boolean containsAccount(String userName,String groupName) throws  NoGroupFoundForAccount;
    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistException;
    public List<Account> displayUserAccountByGroupName(String groupName)throws NoGroupFoundForAccount;
    public    boolean removeUserAccountFromGroup(String userName,String groupName) throws NoGroupFoundForAccount;
    public   boolean updatePasswordForUserAccount(String groupName, String userName, String newPassword)throws  NoGroupFoundForAccount;
    public   String retrievePassword(String userName, String groupName)throws  NoGroupFoundForAccount;
}
