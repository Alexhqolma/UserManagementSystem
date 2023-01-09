package com.example.usermanagementsystem.security;

import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountService userAccountService;

    public CustomUserDetailsService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccountOptional = userAccountService.findByUsername(username);
        if (userAccountOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        UserAccount userAccount = userAccountOptional.get();
        User.UserBuilder builder = User.withUsername(username);
        builder.password(userAccount.getPassword());
        builder.authorities(userAccount.getRoles().stream()
                .map(r -> r.getRoleName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
