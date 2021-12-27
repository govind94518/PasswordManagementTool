package com.epam.pmt.Services;

import com.epam.pmt.dao.Operation;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateGroup {
    final static Logger LOGGER = LogManager.getLogger(CreateGroup.class);

    public static void createAccount() {
        LOGGER.info("......Enter the Account and Password......");
        Scanner input = new Scanner(System.in);
        String account_name = input.next();
        Scanner in = new Scanner(System.in);
        String account_password = in.nextLine();
        CreateUserAccount user = new CreateUserAccount(account_name, account_password);
        LOGGER.info(".... Where you want to store your account....");
        LOGGER.info("Enter 1 - Create New group...");
        LOGGER.info("Enter 2 - Existing Group....");
        Scanner inp = new Scanner(System.in);
        String selection = inp.nextLine();
        switch (selection) {
            case "1":
                createNewGroup(user);
                break;
            case "2":
                existingGroup(user);
                break;
            default:
                LOGGER.info("please type correct input");
                OptionMenu.getUserOption();
        }
    }

    public static void createNewGroup(CreateUserAccount user) {
        LOGGER.info(".....Enter Your New Group Name.....");
        Scanner input = new Scanner(System.in);
        String groupName = input.nextLine();
        Operation.save(groupName, user, true);
        LOGGER.info(".....Account Created Successfully......");
        LOGGER.info("group Name is " + groupName);
        LOGGER.info("user Id is " + user.id + "     and    Password is " + user.password);
        OptionMenu.getUserOption();
    }

    public static void existingGroup(CreateUserAccount user) {
        LOGGER.info(".....Enter Existing Group Name....");
        Scanner input = new Scanner(System.in);
        String groupName = input.nextLine();

        if (Operation.existInsertUserAccount(groupName)) {
            Operation.save(groupName, user, false);
            LOGGER.info(".....Account Created Successfully......");
            LOGGER.info("group Name is " + groupName);
            LOGGER.info("user Id is " + user.id + " ..Password is " + user.password);
        } else {
            LOGGER.info("....Wrong  Group Name....");
        }
        OptionMenu.getUserOption();
    }


}
