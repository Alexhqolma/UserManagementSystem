package com.example.usermanagementsystem.dto.mapper;

public interface ResponseDtoMapper<T, D> {
    D mapToDto(T t);
}
