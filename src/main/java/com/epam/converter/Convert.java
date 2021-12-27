package com.epam.converter;

import com.epam.dto.LoginDTO;
import com.epam.dto.RegisterDTO;
import com.epam.entity.Account;

public class Convert {
    public static Account convertToEntity(RegisterDTO registerDTO) {
        return new Account(registerDTO.getUserName(), registerDTO.getUrl(), registerDTO.getPassword());
    }
    public static Account convertToEntity(LoginDTO loginDTO) {
        return new Account(loginDTO.getUserName(), loginDTO.getPassword());
    }
}