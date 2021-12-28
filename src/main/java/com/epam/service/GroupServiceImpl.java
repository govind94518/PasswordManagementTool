package com.epam.service;
import com.epam.dao.AccountDaoImpl;
import com.epam.dao.GroupDao;
import com.epam.dao.GroupDaoImpl;
import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class GroupServiceImpl implements GroupService {
  //  GroupDao groupDao=new GroupDaoImpl();

    @Autowired
           @Qualifier("groupDaoImpl")
   GroupDao groupDao;

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public  boolean  groupExistOrNot(String groupName)throws NoGroupFoundForAccount
    {
        return  groupDao.containsGroup( groupName);
    }

    public List<Account> findAllRecordByGroupName(String groupName) throws NoRecordFoundForGroup {
        return groupDao.displayAllRecordByGroupName( groupName);
    }

    public boolean updateGroup( String newGroupName,String oldGroupName ) throws NoGroupFoundForAccount
    {
        return  groupDao. updateGroupName( newGroupName, oldGroupName);

    }
    public  List<Group> findAllGroups()throws NoGroupFoundForAccount
    {
        return  groupDao.displayListOfGroups();
    }

    public  boolean deleteGroup (String groupName) throws NoGroupFoundForAccount
    {
        return   groupDao. deleteGroupName(groupName);

    }


}
