package com.epam.dao;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Group;
import com.epam.entity.Account;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import com.epam.repository.AccountRepository;
import com.epam.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDao {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    GroupRepository groupRepository;

    public boolean registerAccount(RegisterDTO registerDTO) throws AccountAlreadyExistsException {
        Account account = new Account();
        account.setUserName(registerDTO.getUserName());
        account.setUrl(registerDTO.getUrl() );
        account.setPassword(registerDTO.getPassword());

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        boolean existGroup = groupRepository.existsByGroupName(registerDTO.getGroupName());
        if (existGroup) {
            throw new AccountAlreadyExistsException();
        }
        Group group = new Group();
        group.setGroupName(registerDTO.getGroupName());
        group.setUserAccounts(accounts);
        account.setGroup(group);
        groupRepository.save(group);
        return true;
    }

    public List<Account> displayUserAccountByGroupName(String groupName) throws NoGroupFoundForAccount {
        List<Account> users = new ArrayList<>();

            List<Group> groupList = (List<Group>) groupRepository.findAll();
            if (groupList.isEmpty())
                throw new NoGroupFoundForAccount();
            for (Group group : groupList) {
                if (group.getGroupName().equals(groupName)) {
                    users = group.getUserAccounts();
                    break;
                }
            }
            if (users.isEmpty()) throw new NoGroupFoundForAccount();
        return users;
    }

    public boolean removeUserAccountFromGroup(String userName, String groupName)throws  NoGroupFoundForAccount{
        boolean isRemoved = false;
            Group group = groupRepository.findByGroupName(groupName);
            List<Account> accountList = group.getUserAccounts();
            for (Account account : accountList)
                if (account.getUserName().equals(userName)) {
                    isRemoved = true;
                    accountRepository.delete(account);
                }
            if (!isRemoved) throw new NoGroupFoundForAccount();
        return isRemoved;
    }

    public boolean updatePasswordForUserAccount(String groupName, String userName, String newPassword) throws NoGroupFoundForAccount {
        boolean isUpdated = false;
            Group group = groupRepository.findByGroupName(groupName);
            List<Account> accountList = group.getUserAccounts();

            for (Account account : accountList) {
                if (account.getUserName().equals(userName)) {
                    account.setPassword(newPassword);
                    accountRepository.save(account);
                    isUpdated = true;
                    break;
                }
            }
            if (!isUpdated) throw new NoGroupFoundForAccount();

        return isUpdated;
    }


    public String retrievePassword(String userName, String groupName) throws   NoRecordFoundForGroup {
        String  password = "";
            boolean isGroupNameExist = groupRepository.existsByGroupName(groupName);
            if (!isGroupNameExist) {
                throw new NoRecordFoundForGroup();
            }
            Group group = groupRepository.findByGroupName(groupName);
                List<Account> accountList = group.getUserAccounts();
                for (Account account : accountList) {
                    if (account.getUserName().equals(userName)) {
                        password = account.getPassword();
                        break;
                    }
                }
        return password;
    }


}


