package com.epam.service;

import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public interface GroupService {
    public  boolean  groupExistOrNot(String groupName)throws NoGroupFoundForAccount;
    public List<Account> findAllRecordByGroupName(String groupName) throws NoRecordFoundForGroup;
    public boolean updateGroup( String newGroupName,String oldGroupName ) throws NoGroupFoundForAccount;
    public  List<Group> findAllGroups()throws NoGroupFoundForAccount;
    public  boolean deleteGroup (String groupName) throws NoGroupFoundForAccount;
}
