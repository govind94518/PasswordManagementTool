package com.epam.pmt.Services;

import com.epam.pmt.dao.Operation;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OptionMenu {

    final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void getUserOption() {

        LOGGER.info("......Select the Account you want to access......");
        LOGGER.info("        Enter 1 -  Create User  Account     ");
        LOGGER.info("        Enter 2 -  Display List Of groups     ");
        LOGGER.info("        Enter 3 -  Display User Account by Group Name ");
        LOGGER.info("        Enter 4 -  Update group name  ");
        LOGGER.info("        Enter 5 -  Delete group name  ");
        LOGGER.info("        Enter 6 -  Exit  ");

        LOGGER.info("please enter  Choice: ");
        Scanner input = new Scanner(System.in);
        String selection = input.nextLine();
        switch (selection) {
            case "1":
                CreateGroup.createAccount();
                break;
            case "2":
                Operation.displayListOfGroups();

                break;
            case "3":
                Operation.displayUserAccountByGroupName();

                break;
            case "4":
                Operation.updateGroupName();
                break;
            case "5":
                Operation.DeleteGroupName();
                break;
            case "6":
                break;
            default:
                LOGGER.info("\n" + "Invalid Choice. please press correct input " + "\n");
        }
        getUserOption();
    }

}

