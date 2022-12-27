package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("select u from UserAccount u join fetch u.roles r where u.id=r.id")
    Optional<UserAccount> findByUserName(String userName);
}
