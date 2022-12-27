package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.request.UserAccountRequestDto;
import com.example.usermanagementsystem.dto.response.RoleResponseDto;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.model.UserAccount;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAccountMapper {
    private final RoleMapper roleMapper;

    public UserAccountMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserAccount mapToModel(UserAccountRequestDto dto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(dto.getUserName());
        userAccount.setPassword(dto.getPassword());
        userAccount.setFirstName(dto.getFirstName());
        userAccount.setLastName(dto.getLastName());
        userAccount.setRoles(new HashSet<>());
        return userAccount;
    }

    public UserAccountResponseDto mapToDto(UserAccount userAccount) {
        UserAccountResponseDto userAccountResponseDto = new UserAccountResponseDto();
        userAccountResponseDto.setId(userAccount.getId());
        userAccountResponseDto.setUserName(userAccount.getUserName());
        userAccountResponseDto.setFirstName(userAccount.getFirstName());
        userAccountResponseDto.setLastName(userAccount.getLastName());
        List<RoleResponseDto> roles = userAccount.getRoles()
                .stream()
                .map(roleMapper::mapToDto)
                .collect(Collectors.toList());
        userAccountResponseDto.setRole(roles);
        userAccountResponseDto.setStatus(userAccount.getStatus());
        return userAccountResponseDto;
    }
}
