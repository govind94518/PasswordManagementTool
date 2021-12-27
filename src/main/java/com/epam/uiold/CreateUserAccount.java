package com.epam.uiold;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUserAccount
{
    public String id;
    public String  password;
    public CreateUserAccount(){};
    final Logger LOGGER=LogManager.getLogger(CreateUserAccount.class);
    public CreateUserAccount(String id, String  password)
    {
        this.id=id;
        this.password=password;
    }
    public String getId() {
        return  id;
    }
    public String getPassword() {
        return password;
    }
    public void display()
    {
        LOGGER.info(" id "+id + " password  "+password);
    }

}
