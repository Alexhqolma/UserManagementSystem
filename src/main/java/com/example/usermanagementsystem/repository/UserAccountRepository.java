package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
