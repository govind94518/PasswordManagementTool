package com.epam.uiold;

import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import com.epam.service.AccountServiceImpl;
import com.epam.service.GroupServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CreateGroup {
    final static Logger LOGGER = LogManager.getLogger(CreateGroup.class);


    @Autowired
        AccountServiceImpl accountService;
    @Autowired
GroupServiceImpl groupService;

    public AccountServiceImpl getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    public GroupServiceImpl getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }
    public void createAccount() throws AccountAlreadyExistsException, NoGroupFoundForAccount, NoRecordFoundForGroup {
        LOGGER.info("......Enter the groupName   ......");
        Scanner input = new Scanner(System.in);
        String groupName = input.nextLine();
        LOGGER.info("......Enter the userName   ......");
        input = new Scanner(System.in);
        String userName = input.nextLine();
        LOGGER.info("......Enter the url   ......");
        input = new Scanner(System.in);
        String url = input.nextLine();
        LOGGER.info("......Enter the password   ......");
        input = new Scanner(System.in);
        String password = input.nextLine();

        if (accountService.registerAccount( new RegisterDTO(groupName, userName, url, password)))
            LOGGER.info("Account Registered Successfully");
        else
            LOGGER.info("Account Registered Successfully");
    }

    public void printListOfGroups() throws NoGroupFoundForAccount {
        List<Group> groupList = groupService.findAllGroups();
        for (Group group : groupList)
            LOGGER.info(group.getGroupName());
    }

    public  void displayUserAccountByGroupName() throws NoRecordFoundForGroup {
        LOGGER.info("Enter group Name");
        Scanner input= new Scanner(System.in);
        String groupName = input.nextLine();
        List<Account>accounts= groupService.findAllRecordByGroupName(groupName);
        for(Account account:accounts) {
            LOGGER.info("username :"+account.getUserName()+"  url:  "+account.getUrl()+"  password: "+account.getPassword());
        }
    }
    public void  updateGroupName() throws NoGroupFoundForAccount {
        LOGGER.info("Enter the old group Name");
        Scanner input= new Scanner(System.in);
        String oldGroupName = input.nextLine();
        LOGGER.info("Enter the new group Name");
        String newGroupName= input.nextLine();
         boolean isUpdated= groupService.updateGroup( newGroupName, oldGroupName );
         if(isUpdated)
             LOGGER.info("Successfully Updated");
         else
             LOGGER.info(" Not  Updated");
    }
    public void deleteGroupName () throws NoGroupFoundForAccount {
        LOGGER.info("Enter the   group Name which you want to delete");
        Scanner input= new Scanner(System.in);
        String groupName = input.nextLine();
        boolean isUpdated= groupService.deleteGroup( groupName );
        if(isUpdated)
            LOGGER.info("Group Deleted Successfully ");
        else
            LOGGER.info(" Not  deleted");
    }
    public void presentGroupOrNot() throws NoGroupFoundForAccount {
        LOGGER.info("Enter the   group Name which you want to check");
        Scanner input= new Scanner(System.in);
        String groupName = input.nextLine();
        boolean isPresent= groupService.groupExistOrNot( groupName );
        if(isPresent)
            LOGGER.info("Group Is Present  Successfully ");
        else
            LOGGER.info("Group is  not  Present ");
    }
        public void  userAccountPresent() throws NoGroupFoundForAccount {

            LOGGER.info("........Enter the  UserAccount which you want to check.........");
            Scanner input= new Scanner(System.in);
            String userAccountName = input.nextLine();
            LOGGER.info("........Enter the  group .........");
            String groupName = input.nextLine();
            boolean isPresent=  accountService. accountPresent( userAccountName,groupName);
            if(isPresent)
                LOGGER.info(" User Account Is Present  Successfully ");
            else
                LOGGER.info("User Account  is  not  Present ");
        }
    public void deleteUserAccount() throws NoGroupFoundForAccount{
        LOGGER.info("........Enter the  UserAccount which you want to deleted.........");
        Scanner input= new Scanner(System.in);
        String userAccountName = input.nextLine();
        LOGGER.info("........Enter the  group .........");
        String groupName = input.nextLine();
       boolean isAccountDeleted= accountService.deleteAccountFromGroup ( userAccountName , groupName) ;
        if(isAccountDeleted)
            LOGGER.info(" User Account Is Deleted Successfully ");
        else
            LOGGER.info("User Account  is  not  Deleted ");
    }
    public void  updatePasswordFromAccount()throws  NoGroupFoundForAccount{
        LOGGER.info("........Enter the  UserAccount for which you want to Change Passoword .........");
        Scanner input= new Scanner(System.in);
        String userName = input.nextLine();
        LOGGER.info("........Enter the  groupName .........");
        String groupName = input.nextLine();
        LOGGER.info("........Enter the  Password.........");
        String newPassword= input.nextLine();
         boolean  isPasswordUpdated=accountService.updatePasswordFromUserAccount( groupName, userName, newPassword);
         if(isPasswordUpdated)
             LOGGER.info(" User Account Password Updated  Successfully ");
         else
             LOGGER.info("User Account Password Updated not  Deleted ");
    }
    public  void   retrievalPasswordFromUserAccount() throws  NoGroupFoundForAccount{
        LOGGER.info("........Enter the  UserAccount for which you want to Change Passoword .........");
        Scanner input= new Scanner(System.in);
        String userName = input.nextLine();
        LOGGER.info("........Enter the  groupName .........");
        String groupName = input.nextLine();
          String password= accountService.retrievalPassword(userName, groupName);
          LOGGER.info("UserAccount password is :"+password);
    }
}
