package com.example.usermanagementsystem.service.impl;

import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.repository.UserAccountRepository;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserAccount> findByUsername(String userName) {
        return userAccountRepository.findByUserName(userName);
    }

    @Override
    public UserAccount save( UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        return userAccountRepository.save(userAccount);
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount findById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id " + id));
    }
}
