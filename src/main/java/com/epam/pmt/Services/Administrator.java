package com.epam.pmt.Services;
import  org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;
import java.util.Scanner;

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

    public  boolean  administratorVerification()
    {
        final Logger LOGGER=LogManager.getLogger(Administrator.class);


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
