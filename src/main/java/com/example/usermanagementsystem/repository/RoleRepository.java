package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(Role.RoleName role);
}
