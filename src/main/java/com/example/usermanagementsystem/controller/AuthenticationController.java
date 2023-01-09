package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.mapper.UserAccountMapper;
import com.example.usermanagementsystem.dto.response.UserAccountResponseDto;
import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.model.UserAccount;
import com.example.usermanagementsystem.security.jwt.JwtTokenProvider;
import com.example.usermanagementsystem.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserAccountMapper userAccountMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserAccountMapper userAccountMapper,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authenticationService = authenticationService;
        this.userAccountMapper = userAccountMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/")
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView userLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserAccountResponseDto userAccountResponseDto)
            throws AuthenticationException {
        UserAccount userAccount = authenticationService.login(userAccountResponseDto.getUserName(),
                userAccountResponseDto.getPassword());
        String token = jwtTokenProvider.createToken(userAccount.getUserName(), userAccount.getRoles().stream()
                .map(r -> r.getRoleName().name()).collect(Collectors.toList()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
