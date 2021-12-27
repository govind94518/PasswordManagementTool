package com.epam.pmt;
import com.epam.pmt.Services.Administrator;
import com.epam.pmt.Services.CreateUserAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CreateUserAccountTest
{

    @Test
    void testCreateUserAccount()
    {
        CreateUserAccount verify=new CreateUserAccount("google.com","qwert123");
        assertEquals("google.com",verify.getId());
        assertEquals("qwert123",verify.getPassword());
    }
}





