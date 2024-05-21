package com.sszm.config;

import com.sszm.model.Authority;
import com.sszm.model.Customer;
import com.sszm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EazyBankUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;
    @Autowired
    public EazyBankUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username).map(customer -> User.withUsername(customer.getEmail())
                .password(customer.getPwd())
                .authorities(customer.getAuthorities().stream().map(Authority::getName).toArray(String[]::new))
                .build()).orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    }
}