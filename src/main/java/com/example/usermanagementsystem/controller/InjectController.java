package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ModelAndView injectData() {
        roleService.save(new Role(Role.RoleName.ADMIN));
        roleService.save(new Role(Role.RoleName.USER));
        List<Role> roles = roleService.findAll();

        UserAccount testAdmin = new UserAccount();
        testAdmin.setUserName("a");
        testAdmin.setPassword("a");
        testAdmin.setRoles(new HashSet<>(roles));
        userAccountService.save(testAdmin);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/inject");

        return modelAndView;
    }
}
