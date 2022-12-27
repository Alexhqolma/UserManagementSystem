package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.response.RoleResponseDto;
import com.example.usermanagementsystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto mapToDto(Role role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getRoleName().name());
        return responseDto;
    }
}
