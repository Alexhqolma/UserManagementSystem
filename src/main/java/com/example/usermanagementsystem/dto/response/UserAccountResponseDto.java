package com.example.usermanagementsystem.dto.response;

import com.example.usermanagementsystem.model.Status;
import java.time.LocalDate;
import java.util.List;

public class UserAccountResponseDto {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Status status;
    private LocalDate createdAt;
    private List<RoleResponseDto> role;

    public List<RoleResponseDto> getRole() {
        return role;
    }

    public void setRole(List<RoleResponseDto> role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
