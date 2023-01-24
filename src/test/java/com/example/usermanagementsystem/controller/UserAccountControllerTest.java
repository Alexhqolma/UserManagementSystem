package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserAccountService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserAccountControllerTest {
    @MockBean
    private UserAccountService userAccountService;

    @MockBean
    private RoleService roleService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldGetAllUsers() {
        List<UserAccount> mockUsers = new ArrayList<>();
        UserAccount userAccount1 = new UserAccount();
        userAccount1.setUserName("User1");
        UserAccount userAccount2 = new UserAccount();
        userAccount2.setUserName("User2");
        UserAccount userAccount3 = new UserAccount();
        userAccount3.setUserName("User3");
        mockUsers.add(userAccount1);
        mockUsers.add(userAccount2);
        mockUsers.add(userAccount3);
        Mockito.when(userAccountService.findAll()).thenReturn(mockUsers);

        RestAssuredMockMvc.when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3));
    }

    @Test
    public void shouldGetUserDetails() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1L);
        Mockito.when(userAccountService.findById(1L)).thenReturn(userAccount);

        RestAssuredMockMvc.when()
                .get("/user/{id}")
                .then()
                .statusCode(200)
                .body("userAccount.getId()", Matchers.equalTo(1));
    }

}