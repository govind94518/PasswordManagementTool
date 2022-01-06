package com.epam.restcontroller;

import com.epam.entity.Account;
import com.epam.entity.Group;
import com.epam.service.AccountService;
import com.epam.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
//
//@WebMvcTest(RestGroupController.class)
//@ContextConfiguration(classes = {RestGroupController.class})
@WithMockUser
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestGroupControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GroupService groupService;
    @MockBean
    AccountService accountService;

    @Test
    void testInputGroupName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputGroupName")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }


    @Test
    void testDisplayListOfGroups() throws Exception {
        List<Group> groups = new ArrayList<>();
        when(groupService.findAllGroups()).thenReturn(groups);
        String uri = "/group/listofgroups";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }

    @Test
    void testDisplayAllRecordByGroupName() throws Exception {
        List<Account> groups = new ArrayList<>();
        when(groupService.findAllRecordByGroupName("rahul")).thenReturn(groups);

        String uri = "/group/displayAllRecordsByGroupName/rahul";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }

    @Test
    void testInputUpdateGroupName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputUpdateGroupName")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    @Test
    void testUpdateGroupName() throws Exception {
        when(groupService.updateGroup("ram", "rahul")).thenReturn(true);
        String uri = "/group/updateGroupName/rahul/ram";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }

    @Test
    void testTakeDeleteGroupName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputDeleteGroupName")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    @Test
    void testDeleteGroupName() throws Exception {
        when(groupService.deleteGroup("ram")).thenReturn(true);
        String uri = "/group/deleteGroupName/ram";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }

    @Test
    void testInputDeleteUserAccount() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputDeleteUserAccount")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    @Test
    void tesDeleteUserAccount() throws Exception {
        when(groupService.updateGroup("ram", "rahul")).thenReturn(true);
        String uri = "/group/deleteUserAccount/rahul/ram";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);

    }
    @Test
    void testDeleteUserAccount() throws Exception {
        when(groupService.updateGroup("ram", "rahul")).thenReturn(true);
        String uri = "/group/deleteUserAccount/rahul/ram";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);

    }

    @Test
    void testInputUpdatePasswordFromAccount() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputUpdatePasswordFromAccount")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    @Test
    void testUpdatePasswordFromAccount() throws Exception {

        when(accountService.updatePasswordFromUserAccount("ram", "rahul", "qwerty@#")).thenReturn(true);
        String uri = "/group/updatePasswordFromAccount/rahul/ram/qwerty@#";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }

    @Test
    void testInputRetrievalPasswordFromUserAccount() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/inputRetrievalPasswordFromUserAccount")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(202, status);
    }

    @Test
    void testRetrievalPasswordFromUserAccount() throws Exception {
        String password = "qwerty123";
        when(accountService.retrievalPassword("Ankit", "PaypalGroup")).thenReturn(password);
        String uri = "/group/retrievalPasswordFromUserAccount/Ankit/PaypalGroup";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }


}
