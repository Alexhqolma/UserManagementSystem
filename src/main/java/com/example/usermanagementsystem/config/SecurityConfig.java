package com.example.usermanagementsystem.config;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.security.filter.UserStatusFilter;
import com.example.usermanagementsystem.security.jwt.JwtConfigurer;
import com.example.usermanagementsystem.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                /*.sessionManagement()
                .enableSessionUrlRewriting(true)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()*/
                .authorizeRequests()
                .antMatchers("/", "/inject")
                .permitAll()
                .antMatchers("/users", "/users/{id}", "/users/{id}", "/users/new", "/users/{id}/edit")
                .hasRole(Role.RoleName.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/**").hasRole(Role.RoleName.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(new UserStatusFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .headers().frameOptions().disable();
    }
}
