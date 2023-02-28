package com.example.usermanagementsystem.mapper;

import com.example.usermanagementsystem.dto.mapper.RoleMapper;
import com.example.usermanagementsystem.dto.response.RoleResponseDto;
import com.example.usermanagementsystem.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleMapperTest {
    private RoleMapper roleMapper;

    @BeforeEach
    public void setup() {
        roleMapper = new RoleMapper();
    }

    @Test
    public void checkMapToDto() {
        Role role = new Role();
        role.setRoleName(Role.RoleName.ADMIN);
        RoleResponseDto expected = new RoleResponseDto();
        expected.setName("ADMIN");
        RoleResponseDto actual = roleMapper.mapToDto(role);
        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}
