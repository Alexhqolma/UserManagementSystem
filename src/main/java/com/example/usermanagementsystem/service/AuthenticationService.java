package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.model.UserAccount;

public interface AuthenticationService {
    UserAccount login(String email, String password) throws AuthenticationException;
}
