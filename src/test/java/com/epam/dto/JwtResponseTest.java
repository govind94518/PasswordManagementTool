package com.epam.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtResponseTest
{
    JwtResponse jwtResponse;
    @BeforeEach
    void setup() {
        jwtResponse= new JwtResponse();
    }

 @Test
    void testToken() {
     jwtResponse= new JwtResponse("abchd.@34smd.@#$nmsd");
        jwtResponse.setToken("abchd.@34smd.@#$nmsd");
        assertEquals("abchd.@34smd.@#$nmsd", jwtResponse.getToken());
    }


}