package com.epam.dao;

import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import com.epam.repository.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
  class AccountDaoImplTest {
    @InjectMocks
    AccountDao accountDaoImpl;
    @Mock
    GroupRepository groupRepository;
    @Test
    void  testRegisterAccountThrowExceptionWhenAccountAlreadyExists() {
        RegisterDTO registerDTO = new RegisterDTO("TATA", "Rahul", "www.leetcode.com","#@*ui*&M");
      when(groupRepository.existsByGroupName("TATA")).thenReturn(true);
        Assertions.assertThrows(AccountAlreadyExistsException.class,() -> accountDaoImpl.registerAccount(registerDTO));
    }

    @Test
    void testRegisterAccountDoesNotThrowException() {
        RegisterDTO registerDTO = new RegisterDTO("TATA", "Rahul", "www.leetcode.com","#@*ui*&M");
        when(groupRepository.existsByGroupName("TATA")).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> accountDaoImpl.registerAccount(registerDTO));
    }


    @Test
    void testDisplayUserAccountByGroupNameThrowException(){
        List<Group> groupList = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(groupList);
        Assertions.assertThrows(NoGroupFoundForAccount.class,() -> accountDaoImpl.displayUserAccountByGroupName("Google"));
    }
    @Test
    void testDisplayUserAccountByGroupNameDoesNotThrowException(){
        List<Group> groupList = new ArrayList<>();
        List<Account>accountList = new ArrayList<>();
        Group group =new Group();
        group.setGroupName("EPAM");
        group.setGroupId(1);
        Account account1=new Account();
        account1.setUserName("PKDC");
        account1.setUrl("www.pksongs.com");
        account1.setPassword("pksongs @#$");
        Account account2=new Account();
        account1.setUserName("PKDC12");
        account1.setUrl("www.pksongs12.com");
        account1.setPassword("pksongs @#$sdcsd");
        accountList.add(account1);
        accountList.add(account2);
        group.setUserAccounts(accountList);
        groupList.add(group);

        when(groupRepository.findAll()).thenReturn(groupList);
        Assertions.assertDoesNotThrow( () -> accountDaoImpl.displayUserAccountByGroupName("EPAM"));
    }

    @Test
     void testRemoveUserAccountFromGroup(){
        List<Account>accountList = new ArrayList<>();
        Group group =new Group();
        group.setGroupName("EPAM");
        group.setGroupId(1);
        Account account1=new Account();
        account1.setUserName("PKDC");
        account1.setUrl("www.pksongs.com");
        account1.setPassword("pksongs @#$");
        Account account2=new Account();
        account1.setUserName("PKDC12");
        account1.setUrl("www.pksongs12.com");
        account1.setPassword("pksongs @#$sdcsd");
        accountList.add(account1);
        accountList.add(account2);
        group.setUserAccounts(accountList);
        when(groupRepository.existsByGroupName("EPAM")).thenReturn(true);
        when(groupRepository.findByGroupName("EPAM")).thenReturn(group);
        Assertions.assertThrows(NullPointerException.class,() -> accountDaoImpl.removeUserAccountFromGroup("PKDC","EPAM"));
    }

    @Test
  void testUpdatePasswordForUserAccount(){
        List<Account>accountList = new ArrayList<>();
        Group group =new Group();
        group.setGroupName("EPAM");
        group.setGroupId(1);
        Account account1=new Account();
        account1.setUserName("PKDC");
        account1.setUrl("www.pksongs.com");
        account1.setPassword("pksongs @#$");
        Account account2=new Account();
        account1.setUserName("PKDC12");
        account1.setUrl("www.pksongs12.com");
        account1.setPassword("pksongs @#$sdcsd");
        accountList.add(account1);
        accountList.add(account2);
        group.setUserAccounts(accountList);
        when(groupRepository.existsByGroupName("EPAM")).thenReturn(true);
        when(groupRepository.findByGroupName("EPAM")).thenReturn(group);
        Assertions.assertThrows(NullPointerException.class,() -> accountDaoImpl.updatePasswordForUserAccount("EPAM","PKDC","PKDC@#$123"));
    }

    @Test
     void testRetrievePasswordDoesThrowException(){
        when(groupRepository.existsByGroupName("Google")).thenReturn(false);
        Assertions.assertThrows( NoRecordFoundForGroup.class,() -> accountDaoImpl.retrievePassword("govind","Google"));
    }
    @Test
   void testRetrievePasswordDoesNotThrowException(){
        List<Account>accountList = new ArrayList<>();
        Group group =new Group();
        group.setGroupName("EPAM");
        group.setGroupId(1);
        Account account1=new Account();
        account1.setUserName("PKDC");
        account1.setUrl("www.pksongs.com");
        account1.setPassword("pksongs @#$");
        Account account2=new Account();
        account1.setUserName("PKDC12");
        account1.setUrl("www.pksongs12.com");
        account1.setPassword("songs@#$sdcsd");
        accountList.add(account1);
        accountList.add(account2);
        group.setUserAccounts(accountList);
        when(groupRepository.existsByGroupName("EPAM")).thenReturn(true);
        when(groupRepository.findByGroupName("EPAM")).thenReturn(group);
        Assertions.assertThrows(NullPointerException.class,() -> accountDaoImpl.retrievePassword("PKDC","EPAM"));
    }


}
