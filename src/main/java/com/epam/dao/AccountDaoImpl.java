package com.epam.dao;
import com.epam.converter.Convert;
import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Group;
import com.epam.entity.Account;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.AccountDoesNotExistException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.uiold.CreateUserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AccountDaoImpl implements AccountDao {
    final Logger LOGGER= LogManager.getLogger(CreateUserAccount.class);
@PersistenceContext
   EntityManagerFactory factory;
    public   boolean registerAccount(RegisterDTO registerDTO) throws AccountAlreadyExistsException, NoGroupFoundForAccount {
        Account accountConverted = Convert.convertToEntity(registerDTO);
        Account account = new Account();
        account.setUserName(accountConverted.getUserName());
        account.setUrl(accountConverted.getUrl());
        account.setPassword(accountConverted.getPassword());
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        Group groupName = new Group();
        groupName.setGroupName( registerDTO.getGroupName());
        groupName.setUserAccounts(accounts);
       account.setGroup(groupName);
        boolean isInserted = false;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(groupName);
            manager.getTransaction().commit();
            isInserted = true;
        } catch (Exception e) {
            if (manager != null) {
                manager.getTransaction().rollback();
            }
        }
        return isInserted;
    }
    public   boolean containsAccount(String userName,String groupName) throws  NoGroupFoundForAccount{
        boolean isPresent=false;
        EntityManager manager=null;
        try
        {
            manager = factory.createEntityManager();
            Query query=manager.createQuery("Select g from Group g");
            List<Group>groupList=query.getResultList();
            Group groupToBeChecked=null;
            for(Group group:groupList) {
                if(group.getGroupName().equals(groupName))
                    groupToBeChecked=group;
            }
            if(groupToBeChecked!=null) {
                List<Account> accountList = groupToBeChecked.getUserAccounts();
                for (Account account : accountList) {
                    if (account.getUserName().equals(userName)) {
                        isPresent = true;
                        break;
                    }
                }
            }
            if(!isPresent)throw new NoGroupFoundForAccount();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            if (manager != null) {
                manager.getTransaction().rollback();
            }
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return isPresent;
        }
    public String validateLogin(LoginDTO loginDTO) throws AccountDoesNotExistException
    {
        boolean isPresent=false;
        EntityManager manager=null;
        try
        {
            manager = factory.createEntityManager();
            Query query=manager.createQuery("Select g from Account g");
            List<Account>accountList=query.getResultList();
           Account accountToBeChecked=null;
            for(Account  account:accountList) {
                if ( account.getUserName().equals(loginDTO.getUserName()) && account.getPassword().equals(loginDTO.getPassword()))
                    return "Successful login";
            }
            throw new AccountDoesNotExistException();

        } catch (Exception e) {
            e.printStackTrace();
            if (manager != null) {
                manager.getTransaction().rollback();
                isPresent = false;
            }
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return "Successful login";
    }
    public   List<Account>  displayUserAccountByGroupName(String groupName)throws NoGroupFoundForAccount
    {
        List<Account> users=new ArrayList<>();
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            List<Group> groupList = manager.createQuery("select  g from Group g ", Group.class).getResultList();
            int id=0;
            Group group=null;
            if(groupList.isEmpty()) {
              LOGGER.info("Group is Empty ");
            }
            else {
                for(Group grp:groupList)
                {
                    if(grp.getGroupName().equals(groupName))
                        group=grp;
                }
                if(group==null)
                    throw new NoGroupFoundForAccount();
                  users= group.getUserAccounts();
            }
        }catch (Exception e) {
            e.printStackTrace();
            if (manager != null) {
                manager.getTransaction().rollback();
            }

        } finally {
            if (manager != null)
                manager.close();
        }
        return  users;
    }


    public    boolean removeUserAccountFromGroup(String userName,String groupName) throws NoGroupFoundForAccount
    {
        EntityManager manager = null;
        int deletedCount=0;
        boolean isRemoved=true;
        try {
            int groupId=0;
                manager = factory.createEntityManager();
                manager.getTransaction().begin();
                Query query = manager.createQuery("Select g From Group g",Group.class);
                List<Group>groupList=query.getResultList();
                for(Group group:groupList)
                {
                    if(group.getGroupName().equals(groupName))
                    {
                        groupId=group.getGroupId();
                        break;
                    }
                }
                if(groupId==0)throw new NoGroupFoundForAccount();
            Query deleteQuery = manager.createQuery("DELETE FROM  Account   WHERE  userName = ?1 and  groupId = ?2");
            deletedCount = deleteQuery.setParameter(1, userName).setParameter(2,groupId).executeUpdate();
            if(deletedCount == 1)
                isRemoved = true;
            else
                throw new NoGroupFoundForAccount();
            manager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            //e.printStackTrace();
            if (manager != null || deletedCount != 1) {
                manager.getTransaction().rollback();
                isRemoved = false;
            }
        } finally {
            if (manager != null)
                manager.close();
        }
        return isRemoved;
        }


    public   boolean updatePasswordForUserAccount(String groupName, String userName, String newPassword)throws  NoGroupFoundForAccount {

        //String encryptedPassword = PasswordSecurity.doneEncryptionApply(newpassword);
        String encryptedPassword=newPassword;
        boolean isUpdated = false;
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();

            Query query = manager.createNamedQuery("get List of Group");
            List<Group> groups = query.getResultList();

            Group groupModifiedPasswordUserAccount = null;
            for (Group group : groups) {
                if (group.getGroupName().equals(groupName))
                    groupModifiedPasswordUserAccount  = group;
            }
            if(groupModifiedPasswordUserAccount ==null)
                throw new NoGroupFoundForAccount();
            int groupId =groupModifiedPasswordUserAccount .getGroupId();

            Query updateQuery = manager.createQuery("UPDATE  Account SET password = ?1" + "WHERE userName = ?2 and groupId = ?3");
            updateQuery.setParameter(1, encryptedPassword);
            updateQuery.setParameter(2, userName);
            updateQuery.setParameter(3, groupId);
            int updateCount = updateQuery.executeUpdate();
            if (updateCount == 1)
                isUpdated = true;
            else
                throw new NoGroupFoundForAccount();

            manager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            //e.printStackTrace();
            if (manager != null) {
                manager.getTransaction().rollback();
                isUpdated = false;
            }

        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return isUpdated;
    }


    public   String retrievePassword(String userName, String groupName)throws  NoGroupFoundForAccount {

        String password = "";
        EntityManager manager = null;
        try {
            manager = factory.createEntityManager();
            manager.getTransaction().begin();
            Query query = manager.createNamedQuery("get List of Group");
            List<Group> groupList = query.getResultList();

            Group groupToBeChecked = null;
            for (Group group : groupList) {
                if (group.getGroupName().equals(groupName))
                    groupToBeChecked = group;
            }
            if(groupToBeChecked!=null) {
                List<Account> accountList = groupToBeChecked.getUserAccounts();
                for (Account account : accountList) {
                    if (account.getUserName().equals(userName)) {
                        password = account.getPassword();
                        break;
                    }
                }
            }
            if(password.isEmpty())   throw new NoGroupFoundForAccount();

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            //e.printStackTrace();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return password;
    }


}


