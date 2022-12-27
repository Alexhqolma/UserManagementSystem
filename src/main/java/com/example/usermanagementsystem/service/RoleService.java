package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    List<Role> findAll();
}
