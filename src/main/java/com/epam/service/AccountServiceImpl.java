package com.epam.service;

import com.epam.dao.AccountDao;
import com.epam.dao.AccountDaoImpl;
import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.AccountDoesNotExistException;
import com.epam.exception.NoGroupFoundForAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Autowired
          @Qualifier("accountDaoImpl")
    AccountDao accountDao;


    public boolean registerAccount(RegisterDTO registerDTO) throws   AccountAlreadyExistsException ,NoGroupFoundForAccount {
        return   accountDao .registerAccount(registerDTO);
    }


    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistException {
        return accountDao .validateLogin(loginDTO);
    }

    public  boolean accountPresent(String userName,String groupName)throws NoGroupFoundForAccount {
        return  accountDao.containsAccount( userName, groupName);
    }

    public List<Account>  allAccountByGroupName(String  groupName)throws NoGroupFoundForAccount {
        return accountDao.displayUserAccountByGroupName(groupName);
    }

        public    boolean deleteAccountFromGroup(String userName,String groupName)throws  NoGroupFoundForAccount {
            return accountDao.removeUserAccountFromGroup( userName,  groupName);
        }

    public   boolean updatePasswordFromUserAccount(String groupName, String userName, String newPassword)throws  NoGroupFoundForAccount {
        return accountDao.updatePasswordForUserAccount( groupName,  userName,  newPassword);
    }
    public   String retrievalPassword(String userName, String groupName)throws  NoGroupFoundForAccount {
        return   accountDao.retrievePassword(userName,groupName);
    }

}
