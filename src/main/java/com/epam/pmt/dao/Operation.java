package com.epam.pmt.dao;
import com.epam.pmt.Services.CreateUserAccount;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.epam.pmt.Services.Main;
import  org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;
public class Operation
{
   static Map<String , ArrayList<CreateUserAccount>> groupUser=new HashMap<>();
    final static Logger LOGGER=LogManager.getLogger(Main.class);
    public   static void save(String groupName,  CreateUserAccount user,boolean  newUser)
    {
        ArrayList<CreateUserAccount> users;
        if(newUser)
            users=new ArrayList<>();
        else {
            users = groupUser.get(groupName);}
            users.add(user);
        groupUser.put(groupName,users);

    }
    public   static boolean existInsertUserAccount(String groupName )
    {
        return groupUser.containsKey(groupName) ;
    }
    public  static void displayListOfGroups()
    {
        Set<Map.Entry<String,ArrayList<CreateUserAccount>>> entries= groupUser.entrySet();
        for(Map.Entry<String,ArrayList<CreateUserAccount>>group_name:entries)
        {
            LOGGER.info(group_name.getKey());
        }

    }
    public static   void displayUserAccountByGroupName()
    {
        LOGGER.info("Enter group name  is ");
        Scanner input= new Scanner(System.in);
        String groupName=input.nextLine();
        if(existInsertUserAccount( groupName ))
        {
            ArrayList<CreateUserAccount> users = groupUser.get(groupName);
            LOGGER.info("  groups user id and password  are.........");
            int value = 1;
            for (CreateUserAccount user : users) {
                LOGGER.info(value + "  person  user_Id   is " + user.id + " and  password  is " + user.password);
                value++;
            }
        }
        else{
            LOGGER.info("this group name does not exist");
              }
    }
    public static void updateGroupName()
    {
        LOGGER.info("Enter group name  Which you want to update ");
        Scanner input= new Scanner(System.in);
        String groupName=input.nextLine();
        if(existInsertUserAccount( groupName )) {

            LOGGER.info("Enter New group name  ");
            Scanner inp = new Scanner(System.in);
            String newGroupName = inp.nextLine();
            ArrayList<CreateUserAccount> users = groupUser.get(groupName);
                groupUser.remove(groupName);
                groupUser.put(newGroupName, users);

        }
        else
        {
            LOGGER.info(".....group does not exist..... ");
        }

    }
    public  static  void DeleteGroupName()
    {

        LOGGER.info("Enter group name  Which you want to Delete ");
        Scanner input= new Scanner(System.in);
        String groupName=input.nextLine();
        if(existInsertUserAccount( groupName )) {
            groupUser.remove(groupName);
            LOGGER.info(".....Deleted Successfully..... ");
        }
        else
        {
            LOGGER.info(".....group does not exist..... ");
        }

    }
}
