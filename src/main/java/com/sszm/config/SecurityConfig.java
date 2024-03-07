package com.sszm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/v1/accounts/myAccount", "/v1/balances/myBalance", "/v1/loans/myLoans", "/v1/cards/myCards")
                        .authenticated()
                        .requestMatchers("/v1/notices/myNotices", "/v1/contact/details")
                        .permitAll()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var admin = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .authorities("admin")
                .build();

        var roUser = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, roUser);
    }

}
