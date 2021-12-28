package com.epam.dao;

import com.epam.entity.Account;
import com.epam.entity.Group;

import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import org.springframework.stereotype.Component;

import java.util.List;

public interface GroupDao {
    public  boolean containsGroup(String groupName)throws NoGroupFoundForAccount;
    public    List<Group> displayListOfGroups() throws NoGroupFoundForAccount;
    public   List<Account> displayAllRecordByGroupName(String groupName)throws NoRecordFoundForGroup;
    public boolean updateGroupName( String newGroupName,String oldGroupName )throws NoGroupFoundForAccount;
    public  boolean deleteGroupName(String groupName) throws NoGroupFoundForAccount;

}
