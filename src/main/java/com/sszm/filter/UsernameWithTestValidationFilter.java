package com.sszm.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static org.springframework.http.HttpHeaders.*;

public class UsernameWithTestValidationFilter implements Filter {

    private static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private final Charset credentialsCharset = StandardCharsets.ISO_8859_1;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) servletRequest;
        var resp = (HttpServletResponse) servletResponse;

        String header = req.getHeader(AUTHORIZATION);
        if(header != null){
            header = header.trim();
            if(StringUtils.startsWithIgnoreCase(header,AUTHENTICATION_SCHEME_BASIC)){
                var base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                try {
                    var decoded = new String(java.util.Base64.getDecoder().decode(base64Token), credentialsCharset);
                    var delim = decoded.indexOf(":");
                    if(delim == -1){
                        throw new IllegalArgumentException("Invalid basic authentication token");
                    }
                    var email = decoded.substring(0, delim);
                    if(email.contains("test")){
                        resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                        return;
                    }
                } catch (IllegalArgumentException e) {
                    throw new IOException("Failed to decode basic authentication token", e);
                }
            }
        }
        filterChain.doFilter(req,resp);
    }

}
