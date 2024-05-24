package com.sszm.filter;

import jakarta.servlet.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(AuthoritiesLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            var username = authentication.getName();
            var authorities = authentication.getAuthorities();
            LOGGER.info(String.format("User %s logged in with authorities %s", username, authorities));
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
