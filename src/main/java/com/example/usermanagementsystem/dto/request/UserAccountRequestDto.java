package com.example.usermanagementsystem.dto.request;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.Status;
import java.time.LocalDate;

public class UserAccountRequestDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;
    private LocalDate createdAt;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}