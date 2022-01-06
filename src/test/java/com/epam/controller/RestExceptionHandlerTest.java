package com.epam.controller;

import com.epam.dao.AccountDao;
import com.epam.exception.NoGroupFoundForAccount;
import com.epam.exception.RestExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RestExceptionHandlerTest {
    @InjectMocks
    RestExceptionHandler restExceptionHandler;
    @Mock
    NoGroupFoundForAccount noGroupFoundForAccount;
@Test
    void handlesNoGroupFoundForAccountExceptionTest() {

        Assertions.assertDoesNotThrow(() -> restExceptionHandler. handlesNoGroupFoundForAccountException(noGroupFoundForAccount));

    }


}
