package com.example.usermanagementsystem.service.impl;

import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.repository.UserAccountRepository;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Optional<UserAccount> findByUsername(String userName) {
        return userAccountRepository.findByUserName(userName);
    }
}
