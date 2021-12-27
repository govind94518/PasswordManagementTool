package com.epam.uiold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Administrator
{
    private  String administratorId="admin";
    private  String  administratorPassword="12345";
    public String getAdmin(String adminId)
    {
       return  administratorId;
    }
    public String getPassword(String password)
    {
        return  administratorPassword;
    }
    final Logger LOGGER=LogManager.getLogger(Administrator.class);
    public  boolean  administratorVerification()
    {
        Scanner sc = new Scanner(System.in);
        LOGGER.info("........Welcome Administrator........  ");

        LOGGER.info("Enter your Administrator_Id");
        String  adId=sc.nextLine();

        LOGGER.info("Enter your Administrator_password");
        String  adPassword=sc.nextLine();

        if(adId.equals(administratorId) && adPassword.equals(administratorPassword))
            return true;
        else
            return  false;
    }
}
