package com.example.usermanagementsystem.service.impl;

import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.AuthenticationService;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserAccountService userAccountService,
                                     PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount login(String userName, String password) throws AuthenticationException {
        Optional<UserAccount> userAccount = userAccountService.findByUsername(userName);
        String encodedPassword = passwordEncoder.encode(password);
        if (userAccount.isEmpty() || userAccount.get().getPassword().equals(encodedPassword)) {
            throw new AuthenticationException("Incorrect username or password!!!");
        }
        return userAccount.orElseThrow(() -> new RuntimeException("Can't find user with username " + userName));
    }
}
