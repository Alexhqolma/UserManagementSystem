package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final RoleService roleService;
    private final UserAccountService userAccountService;

    public InjectController(RoleService roleService,
                            UserAccountService userAccountService) {
        this.roleService = roleService;
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public String injectData() {
        List<Role> roles = roleService.findAll();
        if (!roles.isEmpty()) {
            return "Injection was completed";
        }
        // Save roles
        roleService.save(new Role(Role.RoleName.ADMIN));
        roleService.save(new Role(Role.RoleName.USER));
        roles = roleService.findAll();

        // save user
        UserAccount testAdmin = new UserAccount();
        testAdmin.setUserName("admin");
        testAdmin.setPassword("1234");
        testAdmin.setRoles(new HashSet<>(roles));
        userAccountService.save(testAdmin);

        return "Done!";
    }
}
