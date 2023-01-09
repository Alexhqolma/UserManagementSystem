package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> findByUsername(String userName);

    UserAccount save(UserAccount userAccount);

    List<UserAccount> findAll();

    UserAccount findById(Long id);
}
