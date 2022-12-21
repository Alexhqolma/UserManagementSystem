package com.example.usermanagementsystem.model;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
