package com.epam.dao;

import com.epam.entity.Group;
import com.epam.entity.Account;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;

import com.epam.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class GroupDao {
    @Autowired
    GroupRepository groupRepository;

    public boolean containsGroup(String groupName) throws NoGroupFoundForAccount {
        boolean isExist = groupRepository.existsByGroupName(groupName);
        if (!isExist)
            throw new NoGroupFoundForAccount();
        return isExist;
    }

    public List<Group> displayListOfGroups() throws NoGroupFoundForAccount {
        List<Group> groupList = new ArrayList<>();
        groupList = (List<Group>) groupRepository.findAll();
        if (groupList.isEmpty()) throw new NoGroupFoundForAccount();
        return groupList;
    }

    public List<Account> displayAllRecordByGroupName(String groupName) throws NoRecordFoundForGroup {
        List<Account> accounts = new ArrayList<>();
        boolean isGroupNameExist = groupRepository.existsByGroupName(groupName);
        if (!isGroupNameExist) throw new NoRecordFoundForGroup();
        Group group = groupRepository.findByGroupName(groupName);
        accounts.addAll(group.getUserAccounts());
        return accounts;
    }

    public boolean updateGroupName(String newGroupName, String oldGroupName) throws NoGroupFoundForAccount {
        AtomicBoolean isOldGroupExist = new AtomicBoolean(false);
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        if (groupList.isEmpty())
            throw new NoGroupFoundForAccount();
        groupList.forEach(group -> {
            if (group.getGroupName().equals(oldGroupName)) {
                group.setGroupName(newGroupName);
                groupRepository.save(group);
                isOldGroupExist.set(true);
            }
        });
        if (!isOldGroupExist.get())
            throw new NoGroupFoundForAccount();

        return isOldGroupExist.get();
    }

    public boolean deleteGroupName(String groupName) throws NoGroupFoundForAccount, NoRecordFoundForGroup {
        AtomicBoolean isGroupDeleted = new AtomicBoolean(false);
        List<Group> groupList = (List<Group>) groupRepository.findAll();
        if (groupList.isEmpty())
            throw new NoRecordFoundForGroup();
        groupList.forEach(group -> {
            if (group.getGroupName().equals(groupName)) {
                isGroupDeleted.set(true);
                groupRepository.delete(group);
            }
        });
        if (!isGroupDeleted.get()) throw new NoGroupFoundForAccount();
        return isGroupDeleted.get();
    }
}