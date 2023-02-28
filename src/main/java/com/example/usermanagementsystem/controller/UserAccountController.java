package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.mapper.UserAccountMapper;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserAccountService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView getAllUsersPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserAccountResponseDto> dtos = userAccountService.findAll().stream()
                .map(userAccountMapper::mapToDto)
                .collect(Collectors.toList());
        modelAndView.setViewName("/users");
        modelAndView.addObject("allUsers", dtos);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUserDetailsPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserAccountResponseDto userAccountResponseDto =
                userAccountMapper.mapToDto(userAccountService.findById(id));
        modelAndView.setViewName("/userDetails");
        modelAndView.addObject("user", userAccountResponseDto);
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView getAddNewUserPage(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addNewUser");
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView addUser(@Valid @ModelAttribute("userAccount") UserAccount userAccount,
                                BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addNewUser");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        userAccountService.save(userAccount);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEditUserPage(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("userAccount", new UserAccount());
        UserAccountResponseDto userAccountResponseDto =
                userAccountMapper.mapToDto(userAccountService.findById(id));
        modelAndView.setViewName("/userEdit");
        modelAndView.addObject("user", userAccountResponseDto);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editUser(@ModelAttribute("userAccount") UserAccount userAccount ) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        userAccountService.save(userAccount);
        return modelAndView;
    }
}
