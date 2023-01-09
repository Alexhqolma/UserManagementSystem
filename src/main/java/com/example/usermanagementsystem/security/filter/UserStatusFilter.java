package com.example.usermanagementsystem.security.filter;

import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserStatusFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /*var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("X-HEADER");
        if (StringUtils.isEmpty(authHeader)) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
