package com.example.usermanagementsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return role;
    }

    public enum RoleName {
        ADMIN,
        USER
    }

    public void setRoleName(RoleName role) {
        this.role = role;
    }
}
