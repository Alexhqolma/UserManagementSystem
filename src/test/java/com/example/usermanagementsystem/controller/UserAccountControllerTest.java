package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.UserAccountService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserAccountControllerTest {
    @MockBean
    private UserAccountService userAccountService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldGetAllUsers() {
        Mockito.when(userAccountService.findAll()).thenReturn(mockUsers());
        List<UserAccount> result = userAccountService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(mockUsers().get(1).getUserName(), result.get(1).getUserName());
    }

    @Test
    public void shouldReturnDetailsUser() {
        Mockito.when(userAccountService.findById(1L)).thenReturn(mockUsers().get(0));
        UserAccount result = userAccountService.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockUsers().get(0).getId(), result.getId());
    }

    @Test
    public void shouldAddUserToDB() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Billy");
        Mockito.when(userAccountService.save(userAccount)).thenReturn(mockUsers().get(2));
        UserAccount result = userAccountService.save(userAccount);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUserName(), mockUsers().get(2).getUserName());
    }

    private List<UserAccount> mockUsers() {
        List<UserAccount> mockUsers = new ArrayList<>();
        UserAccount userAccount1 = new UserAccount();
        userAccount1.setUserName("User1");
        userAccount1.setId(1L);
        UserAccount userAccount2 = new UserAccount();
        userAccount2.setUserName("User2");
        UserAccount userAccount3 = new UserAccount();
        userAccount3.setUserName("Billy");
        mockUsers.add(userAccount1);
        mockUsers.add(userAccount2);
        mockUsers.add(userAccount3);
        return mockUsers;
    }

}