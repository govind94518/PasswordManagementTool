package com.epam.restcontroller;

import com.epam.controller.RecordController;
import com.epam.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.epam.dto.RegisterDTO;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(RecordController.class)
@ContextConfiguration(classes = {RecordController.class})
class RestRecordControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountService accountService;

    @Test
    void testMenuForm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/record/registerAccount")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    void testCreateAccount2() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("TATA", "Rahul", "www.leetcode.com", "#@*ui*&M");
        when(accountService.registerAccount(registerDTO)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/record/createAccount/registerDTO")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void createProduct() throws Exception {
        String uri = "/record/createAccount";
        RegisterDTO registerDTO = new RegisterDTO("TATA", "Rahul", "www.leetcode.com", "#@*ui*&M");
        String inputJson = mapToJson(registerDTO);
        when(accountService.registerAccount(registerDTO)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
}
