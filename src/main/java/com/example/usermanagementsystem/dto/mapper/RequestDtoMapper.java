package com.example.usermanagementsystem.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
