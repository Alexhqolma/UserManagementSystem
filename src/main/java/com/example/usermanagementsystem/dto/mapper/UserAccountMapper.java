package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.request.UserAccountRequestDto;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.model.UserAccount;

public class UserAccountMapper implements RequestDtoMapper<UserAccountRequestDto, UserAccount>,
        ResponseDtoMapper<UserAccountResponseDto, UserAccount>{
    @Override
    public UserAccount mapToModel(UserAccountRequestDto dto) {
        return null;
    }

    @Override
    public UserAccount mapToDto(UserAccountResponseDto userAccountResponseDto) {
        return null;
    }
}
