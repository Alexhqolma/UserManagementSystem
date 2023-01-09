package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.mapper.UserAccountMapper;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;
    private final RoleService roleService;

    public UserAccountController(UserAccountService userAccountService,
                                 UserAccountMapper userAccountMapper,
                                 RoleService roleService) {
        this.userAccountService = userAccountService;
        this.userAccountMapper = userAccountMapper;
        this.roleService = roleService;
    }

    @GetMapping
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserAccountResponseDto> list = userAccountService.findAll().stream()
                .map(userAccountMapper::mapToDto)
                .collect(Collectors.toList());
        modelAndView.setViewName("/users");
        modelAndView.addObject("allusers", list);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView userDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserAccountResponseDto userAccountResponseDto =
                userAccountMapper.mapToDto(userAccountService.findById(id));
        modelAndView.setViewName("/userdetails");
        modelAndView.addObject("user", userAccountResponseDto);
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView userNew() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/usernew");
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView userAdd(@RequestParam String userName,
                                @RequestParam String userPassword,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int status,
                                @RequestParam int role) {
        ModelAndView modelAndView = new ModelAndView();
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userName);
        userAccount.setPassword(userPassword);
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setStatus(status == 0 ? Status.ACTIVE : Status.INACTIVE);
        userAccount.setCreatedAt(LocalDate.now());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByRole(role == 0 ? Role.RoleName.ADMIN : Role.RoleName.USER);
        roles.add(userRole);
        userAccount.setRoles(roles);
        userAccountService.save(userAccount);
        modelAndView.setViewName("/usernew");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView userEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserAccountResponseDto userAccountResponseDto =
                userAccountMapper.mapToDto(userAccountService.findById(id));
        modelAndView.setViewName("/useredit");
        modelAndView.addObject("user", userAccountResponseDto);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView userEdited(@PathVariable Long id,
                                   @RequestParam String userName,
                                   @RequestParam String userPassword,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int status ) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        userAccount.setUserName(userName);
        userAccount.setPassword(userPassword);
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setStatus(status == 0 ? Status.ACTIVE : Status.INACTIVE);
        userAccountService.save(userAccount);
        return modelAndView;
    }
}
