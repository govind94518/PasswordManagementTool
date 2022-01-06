package com.epam.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtRequestTest {
    JwtRequest jwtRequest;

    @BeforeEach
    void setup() {
        jwtRequest = new JwtRequest();
    }

    @Test
    void testUserName() {
        jwtRequest.setUsername("Rahul");
        assertEquals("Rahul", jwtRequest.getUsername());
    }

    @Test
    void testPassword() {
        jwtRequest.setPassword("Rahul123");
        assertEquals("Rahul123", jwtRequest.getPassword());
    }

    @Test
    void toStringTest1(){
        jwtRequest=new JwtRequest("Rahul","Rahul123");
        String toString = jwtRequest.toString();
        assertTrue(toString.contains("Rahul"));
        assertTrue(toString.contains("Rahul123"));
    }



}