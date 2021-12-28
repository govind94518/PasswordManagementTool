package com.epam.dao;
import com.epam.entity.Group;
import com.epam.entity.Account;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;

import com.epam.repository.GroupRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupDao {
    @Autowired
    GroupRepository groupRepository;
    public boolean containsGroup(String groupName) throws NoGroupFoundForAccount {
          boolean isExist = groupRepository. existsByGroupName(groupName);
            if (!isExist)
                throw new NoGroupFoundForAccount();
        return isExist ;
    }

    public List<Group> displayListOfGroups() throws NoGroupFoundForAccount {
        List<Group> groupList = new ArrayList<>();
            groupList = (List<Group>) groupRepository.findAll();
            if (groupList.isEmpty())  throw new NoGroupFoundForAccount();
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
        boolean isOldGroupExist = false;
            List<Group> groupList = (List<Group>) groupRepository.findAll();
            if (groupList.isEmpty())
                throw new NoGroupFoundForAccount();

            for (Group group : groupRepository.findAll()) {
                if (group.getGroupName().equals(oldGroupName)) {
                    group.setGroupName(newGroupName);
                    groupRepository.save(group);
                    isOldGroupExist = true;
                    break;
                }
            }
            if (!isOldGroupExist)
                throw new NoGroupFoundForAccount();

        return isOldGroupExist ;
    }

    public boolean deleteGroupName(String groupName) throws NoGroupFoundForAccount, NoRecordFoundForGroup {
        boolean isGroupDeleted = false;
            List<Group> groupList = (List<Group>) groupRepository.findAll();
            if (groupList.isEmpty())
                throw new NoRecordFoundForGroup();
            for (Group group : groupList)
                if (group.getGroupName().equals(groupName)) {
                    isGroupDeleted = true;
                    groupRepository.delete(group);
                    break;
                }
            if (!isGroupDeleted) throw new NoGroupFoundForAccount();
        return isGroupDeleted;
    }
}