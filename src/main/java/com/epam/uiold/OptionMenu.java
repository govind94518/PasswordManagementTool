package com.epam.uiold;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.NoRecordFoundForGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class OptionMenu {
    final static Logger LOGGER=LogManager.getLogger(OptionMenu.class);
    @Autowired
    CreateGroup createGroup;
    public CreateGroup getCreateGroup() {
        return createGroup;
    }

    public void setCreateGroup(CreateGroup createGroup) {
        this.createGroup = createGroup;
    }


    public   void getUserOption() throws AccountAlreadyExistsException, NoGroupFoundForAccount, NoRecordFoundForGroup {


        LOGGER.info("......Select the Account you want to access......");
        LOGGER.info("        Enter 1 -  Create User  Account     ");
        LOGGER.info("        Enter 2 -  Display List Of groups     ");
        LOGGER.info("        Enter 3 -  Display User Account by Group Name ");
        LOGGER.info("        Enter 4 -  Update group name  ");
        LOGGER.info("        Enter 5 -  Delete group name  ");
        LOGGER.info("        Enter 6 -   Group present or not   ");
        LOGGER.info("        Enter 7 -  Check  User Account is  present or not   ");
        LOGGER.info("        Enter 8 -  Delete  User Account     ");
        LOGGER.info("        Enter 9 -  Change  User Account Password    ");
        LOGGER.info("        Enter  10 -  SEE  your  User Account Password     ");

        LOGGER.info("        Enter 11 -  Exit  ");

        LOGGER.info("please enter  Choice: ");
        Scanner input= new Scanner(System.in);
        String selection = input.nextLine();

        switch (selection) {
            case "1":
                createGroup .createAccount();
                break;
           case "2":
               createGroup.printListOfGroups();
                break;
             case "3":
                 createGroup.displayUserAccountByGroupName();
                break;
         case  "4":
             createGroup.updateGroupName();
                break;
            case  "5":
            createGroup.deleteGroupName ();
                break;
            case"6":
                createGroup. presentGroupOrNot();
                break;
                case"7":
                createGroup.userAccountPresent();
                break;
            case"8":
                createGroup.deleteUserAccount();
                break;
            case"9":
                createGroup.updatePasswordFromAccount();
                break;
            case"10":
                createGroup. retrievalPasswordFromUserAccount();
                break;

            default:
                LOGGER.info("\n" + "Invalid Choice. please press correct input " + "\n");
        }
        getUserOption();
    }

    /* Create User Account */
   /* public void create_Account()
    {
        System.out.println("......Enter the Account and Password......");
        Scanner input= new Scanner(System.in);
        String account_name=input.next();
        Scanner in= new Scanner(System.in);
        String account_password=in.nextLine();
        CreateUserAccount user=new CreateUserAccount(account_name,account_password);
        System.out.println(".... Where you want to store your account....");
        System.out.println("Enter 1 - Create New group...");
        System.out.println("Enter 2 - Existing Group....");
        int selection = input.nextInt();
        switch (selection) {
            case 1:
                CreateGroup createNewGroup = new CreateGroup();
                break;
            case 2:
                existingGroup(user);
                break;
            default:
                getUserOption();
        }
    }*/
/* Create New Group For User Account

public  void    createNewGroup( CreateUserAccount  user)
{
    System.out.println(".....Enter Your New Group Name.....");
    Scanner input= new Scanner(System.in);
    String groupName=input.nextLine();
    ArrayList<CreateUserAccount>users=new ArrayList<>();
    users.add(user);
    groupUser.put(groupName,users);
    System.out.println(".....Account Created Successfully......");
    System.out.println("group Name is "+groupName);
    System.out.println("user Id is "+user.id+"     and    Password is "+user.password);

    getUserOption();
}
*/
    /* Create New Group For User Account */

  /*  public  void    existingGroup( CreateUserAccount  user)
    {
        System.out.println(".....Enter Existing Group Name....");
        Scanner input= new Scanner(System.in);
        String groupName=input.nextLine();
        ArrayList<CreateUserAccount> users= (ArrayList<CreateUserAccount>) groupUser.get(groupName);
        users.add(user);
        groupUser.put(groupName,users);

        System.out.println(".....Account Created Successfully......");
        System.out.println("group Name is "+groupName);
        System.out.println("user Id is "+user.id+" ..Password is "+user.password);

        getUserOption();
    }*/

/*   display    List     Of Groups      */
 /*   public  void displayListOfGroups()
    {
       Set<Map.Entry<String,ArrayList<CreateUserAccount>>>entries= groupUser.entrySet();
       for(Map.Entry<String,ArrayList<CreateUserAccount>>group_name:entries)
       {
           System.out.println(group_name.getKey());
       }
        getUserOption();
    }
*/
/*  Display User Account by Account  Name */

   /* public  void displayUserAccountByGroupName()
    {
        System.out.println("Enter group name  is ");
        Scanner input= new Scanner(System.in);
        String groupName=input.nextLine();
        ArrayList<CreateUserAccount>users=groupUser.get(groupName);
        System.out.println("  groups user id and password4  are.........");
        int value=1;
        for(CreateUserAccount user:users)
        {
            System.out.println(value+"  person  user_Id   is "+user.id+" and  password  is "+user.password);
           value++;
        }
        getUserOption();
    }*/
          /* Update group name */
     /*     public  void updateGroupName()
          {
              System.out.println("Enter group name  Which you want to update ");
              Scanner input= new Scanner(System.in);
              String groupName=input.nextLine();
              System.out.println("Enter New group name  ");
              Scanner inp= new Scanner(System.in);
              String newgroupName=inp.nextLine();
              ArrayList<CreateUserAccount>users=groupUser.get(groupName);
              if(groupUser.remove(groupName)!=null) {
                  groupUser.remove(groupName);
                  groupUser.put(newgroupName, users);
              }
              else
              {
                  System.out.println(".....group does not exist..... ");
              }

          }*/
        /* Delete group name */
     /*   public  void DeleteGroupName()
        {
            System.out.println("Enter group name  Which you want to Delete ");
            Scanner input= new Scanner(System.in);
            String groupName=input.nextLine();
            if(groupUser.remove(groupName)!=null) {
                groupUser.remove(groupName);
            }
            else
            {
                System.out.println(".....group does not exist..... ");
            }
            getUserOption();
        }*/

}

