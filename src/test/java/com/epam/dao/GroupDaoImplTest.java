package com.epam.dao;
import com.epam.entity.Account;
import com.epam.entity.Group;

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
 class GroupDaoImplTest {

    @InjectMocks
    GroupDao groupDaoImpl;

    @Mock
    GroupRepository groupRepository;

 @Test
 void testContainsGroup(){

     when(groupRepository.existsByGroupName("Google")).thenReturn(true);
     Assertions.assertDoesNotThrow(() -> groupDaoImpl.containsGroup("Google"));
 }
    @Test
    void testDoesNotContainsGroup(){

        when(groupRepository.existsByGroupName("Google")).thenReturn(false);
        Assertions.assertThrows(NoGroupFoundForAccount.class,() -> groupDaoImpl.containsGroup("Google"));
    }
    @Test
    void testDisplayListOfGroups(){
        List<Group> groupList = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(groupList);
        Assertions.assertThrows(NoGroupFoundForAccount.class,() -> groupDaoImpl.displayListOfGroups());
    }

    @Test
    void testDoesNotDisplayListOfGroups(){
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
        Assertions.assertDoesNotThrow(() -> groupDaoImpl.displayListOfGroups());
    }
    //52
    @Test
    void testDisplayAllRecordByGroupNameThrows(){

        when(groupRepository.existsByGroupName("Google")).thenReturn(false);
        Assertions.assertThrows(NoRecordFoundForGroup.class,() -> groupDaoImpl.displayAllRecordByGroupName("Google"));
    }
    @Test
    void testDisplayAllRecordByGroupName(){
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
        Assertions.assertThrows(NullPointerException.class,() -> groupDaoImpl.displayAllRecordByGroupName("EPAM"));
    }

    //77
    @Test
      void  testUpdateGroupNameThrowsException(){
         List<Group> groupList = new ArrayList<>();
          when(groupRepository.findAll()).thenReturn(groupList);
         Assertions.assertThrows(NoGroupFoundForAccount.class,() -> groupDaoImpl.updateGroupName("GoogleSearch","Google"));
    }
    @Test
    void  testUpdateGroupNameDoesNotThrowsException(){
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
        Assertions.assertDoesNotThrow(() -> groupDaoImpl.updateGroupName("GoogleSearch","EPAM"));
    }




    @Test
      void  testDeleteGroupNameThrowException(){
        List<Group> groupList = new ArrayList<>();
        when(groupRepository.findAll()).thenReturn(groupList);
        Assertions.assertThrows(NoRecordFoundForGroup.class,() -> groupDaoImpl.deleteGroupName("Google"));
    }
    @Test
    void  testDeleteGroupNameDoesNotThrowException(){
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
        Assertions.assertDoesNotThrow( () -> groupDaoImpl.deleteGroupName("EPAM"));
    }


}