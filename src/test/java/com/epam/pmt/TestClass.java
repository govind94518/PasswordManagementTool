package com.epam.pmt;
import com.epam.pmt.Services.Administrator;

import com.epam.pmt.Services.CreateUserAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestClass
{
       @Test
       void testAdministrator()
        {
            Administrator verify=new Administrator();
            assertEquals("admin",verify.getAdmin("admin"));
            assertEquals("12345",verify.getPassword("12345"));
       }
    @Test
    void testCreateUserAccount()
    {
        CreateUserAccount verify=new CreateUserAccount("google.com","qwert123");
        assertEquals("google.com",verify.getId());
        assertEquals("qwert123",verify.getPassword());
    }
}



