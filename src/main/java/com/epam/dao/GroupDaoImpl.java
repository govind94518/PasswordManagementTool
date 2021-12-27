package com.epam.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.epam.entity.Group;
import com.epam.entity.Account;
import com.epam.exception.GroupShouldNotContainsRecords;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import  org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {
    final static Logger LOGGER = LogManager.getLogger(GroupDaoImpl.class);
    @PersistenceContext
   EntityManagerFactory factory;
    public  boolean containsGroup(String groupName)throws NoGroupFoundForAccount {

        boolean isPresent = true;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            Query query = manager.createNamedQuery("get List of GroupNames");
            List<String> groupNamesList = query.getResultList();

            if ( !groupNamesList.contains(groupName))
               throw  new NoGroupFoundForAccount();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            if (manager != null) {
                manager.getTransaction().rollback();
                isPresent = false;
            }

        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return isPresent;
    }

    public    List<Group> displayListOfGroups() throws NoGroupFoundForAccount{
        EntityManager manager = null;
        List<Group>groupList=new ArrayList<>();
        try {
            manager = factory.createEntityManager();
              groupList = manager.createQuery("Select g from Group g", Group.class).getResultList();
            if (groupList.isEmpty())
             throw new NoGroupFoundForAccount();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            if (manager != null) {
                manager.getTransaction().rollback();
            }

        } finally {
            if (manager != null)
                manager.close();
        }
        return groupList;
    }

    public   List<Account> displayAllRecordByGroupName(String groupName)throws NoRecordFoundForGroup
    {
        List<Account>accounts=new ArrayList<>();
        EntityManager manager = null;
        try {

            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            List<Group> groupList = manager.createQuery("select  g from Group g ", Group.class).getResultList();
             int id=0;
             Group group=null;
            if(groupList.isEmpty()) {
                throw  new NoRecordFoundForGroup();
            }
            else {
                for(Group grp:groupList)
                {
                    if(grp.getGroupName().equals(groupName))
                      group=grp;
                }
                if(group!=null)
           accounts= group.getUserAccounts();
                else
                    throw new NoRecordFoundForGroup();

            }
        }catch (Exception e) {
            LOGGER.info(e.getMessage());
         //   e.printStackTrace();
            if (manager != null) {
                manager.getTransaction().rollback();
            }

        } finally {
            if (manager != null)
                manager.close();
        }
       return  accounts;
    }
    public boolean updateGroupName( String newGroupName,String oldGroupName )throws NoGroupFoundForAccount
    {
        EntityManager manager = null;
        int updatedGroupCount = 0;
        boolean isGroupUpdated= true;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            List<Group> groupList = manager.createQuery("select  g from Group g ", Group.class).getResultList();
            boolean isOldGroupExist=false;
            for(Group group:groupList)
            {
                if(group.getGroupName().equals(oldGroupName)) {
                    isOldGroupExist=true;
                    break;
                }
            }
            if(!isOldGroupExist)
                throw new NoGroupFoundForAccount();
            Query query = manager.createQuery(" UPDATE  Group  SET groupName = ?1 WHERE groupName =?2" );
           query.setParameter(1, newGroupName);
           query.setParameter(2,oldGroupName);
       //     updatedGroupCount= query.setParameter(oldGroupName,  newGroupName).executeUpdate();
            updatedGroupCount  = query.executeUpdate();
            if (updatedGroupCount  != 1)
                throw new  NoGroupFoundForAccount();
            manager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
         //   e.printStackTrace();
            if (manager != null ||  updatedGroupCount != 1) {
                manager.getTransaction().rollback();
                isGroupUpdated= false;
            }
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return isGroupUpdated;
    }

    public  boolean deleteGroupName(String groupName) throws NoGroupFoundForAccount  {
        EntityManager manager = null;
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        int updatedGroupCount = 0;
        boolean isGroupdeleted= true;
        try {
            List<Group> groupList = manager.createQuery("select  g from Group g ", Group.class).getResultList();
            int groupId=0;
            for(Group group:groupList)
            {
                if(group.getGroupName().equals(groupName))
                {
                    groupId=group.getGroupId();
                    break;
                }
            }
            if(groupId==0)
               throw new  NoGroupFoundForAccount();

       //  removeUserAccountFromGroupId(groupId);
            int deletedCount=0;
            Query query = manager.createQuery("Select g From  Account g", Account.class);
            List<Account>userAccountList=query.getResultList();
            for(Account userAccount:userAccountList)
            {
                if(userAccount.getGroup().getGroupId()==groupId)
                {
                    Query deleteQuery = manager.createQuery("DELETE FROM  Account   WHERE  userName = ?1 and  groupId = ?2");
                    deletedCount = deleteQuery.setParameter(1, userAccount.getUserName()).setParameter(2,groupId).executeUpdate();
                }
            }


           query = manager.createQuery(" DELETE  FROM Group  WHERE groupName= ?1 and groupId=?2");
            updatedGroupCount  = query.setParameter(1, groupName).setParameter(2,groupId).executeUpdate();
            manager.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
      //  e.printStackTrace();
       if (manager != null ||  updatedGroupCount != 1) {
            manager.getTransaction().rollback();
           isGroupdeleted= false;
        }
    } finally {
        if (manager != null)
            manager.close();
   }
        return isGroupdeleted;
    }
}