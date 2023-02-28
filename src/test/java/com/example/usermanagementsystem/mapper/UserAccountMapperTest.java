package com.example.usermanagementsystem.mapper;

import com.example.usermanagementsystem.dto.mapper.RoleMapper;
import com.example.usermanagementsystem.dto.mapper.UserAccountMapper;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class UserAccountMapperTest {
    private RoleMapper roleMapper;
    private UserAccountMapper userAccountMapper;

    @BeforeEach
    public void setup() {
        roleMapper = new RoleMapper();
        userAccountMapper = new UserAccountMapper(roleMapper);
    }

    @Test
    public void checkMapToDto() {
        Role role = new Role();
        role.setRoleName(Role.RoleName.ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Bob");
        userAccount.setRoles(roles);
        UserAccountResponseDto expected = new UserAccountResponseDto();
        expected.setUserName(userAccount.getUserName());
        expected.setRole(userAccount.getRoles()
                .stream()
                .map(roleMapper::mapToDto)
                .collect(Collectors.toList()));
        UserAccountResponseDto actual = userAccountMapper.mapToDto(userAccount);
        Assertions.assertEquals(expected.getUserName(), actual.getUserName());
    }
}
