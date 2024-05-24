package com.sszm.filter;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthorizationProgressLoggingFilter implements Filter {

    final Logger LOGGER = Logger.getLogger(AuthorizationProgressLoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Authorization in progress...");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
