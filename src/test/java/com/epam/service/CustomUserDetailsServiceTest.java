package com.epam.service;

import com.epam.dto.RegisterDTO;
import com.epam.exception.AccountAlreadyExistsException;
import com.epam.exception.NoGroupFoundForAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {
    @InjectMocks
    CustomUserDetailsService customUserDetailsService;


    @Test
    void testLoadUserByUsername()  {
        Assertions.assertDoesNotThrow(() -> customUserDetailsService.loadUserByUsername("govind"));
    }
    @Test
    void testLoadUserByUsernameThrowsException()  {
        Throwable exception = assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("govind1"));
        assertEquals("User not Found !!", exception.getMessage());

    }


}
