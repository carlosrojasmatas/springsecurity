package com.sszm.config;

import com.sszm.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private String[] securedEndpoints = {
            "/v1/accounts/myAccount",
            "/v1/balances/myBalance",
            "/v1/loans/myLoans",
            "/v1/cards/myCards",
            "/v1/user/details"
    };

    private String[] openEndpoints = {
            "/v1/notices",
            "/v1/contact/save",
            "/v1/user/signup",
    };

    private String[] allowedDomains={
            "http://localhost:4200"
    };


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //basic matchers
        http.authorizeHttpRequests((requests) ->
                requests
//                        .requestMatchers("/v1/accounts/myAccount").hasAuthority("VIEWACCOUNT")
//                        .requestMatchers("/v1/balances/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                        .requestMatchers("/v1/loans/myLoans").hasAuthority("VIEWLOANS")
//                        .requestMatchers("/v1/cards/myCards").hasAuthority("VIEWCARDS")
                        .requestMatchers("/v1/accounts/myAccount").hasRole("USER")
                        .requestMatchers("/v1/balances/myBalance").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/v1/loans/myLoans").hasRole("USER")
                        .requestMatchers("/v1/cards/myCards").hasRole("USER")
                        .requestMatchers("/v1/user/details").authenticated()
                        .requestMatchers(openEndpoints).permitAll()
        );
        //session management
        http.securityContext(ctx ->
                ctx.requireExplicitSave(false));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        //csrf
        var csrfAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfAttributeHandler.setCsrfRequestAttributeName("_csrf");
        http.csrf(config -> config.ignoringRequestMatchers(openEndpoints).csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
//        http.csrf(config -> config.disable());
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        http.cors((cors) -> cors.configurationSource(request -> {
            var corsConfig = new CorsConfiguration();
            corsConfig.setAllowCredentials(true);
            corsConfig.setAllowedHeaders(Collections.singletonList("*"));
            corsConfig.setAllowedMethods(Collections.singletonList("*"));
            corsConfig.setMaxAge(3600L);
            corsConfig.setAllowedOrigins(List.of(allowedDomains));
            return corsConfig;
        }));
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


//    public UserDetailsService jdbcUserDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
