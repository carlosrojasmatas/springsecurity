package com.sszm.controller;

import com.sszm.model.Customer;
import com.sszm.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/user")
public class LoginController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("details")
    public ResponseEntity<Customer> getUserDetails(Authentication authentication) {
        if(Objects.isNull(authentication)){
            return ResponseEntity.ok(new Customer());
        }
        return customerRepository.findByEmail(authentication.getName()).map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("signup")
    public ResponseEntity<String> register(@RequestBody Customer customer){
        customerRepository.save(customer);
        try {
            customerRepository.save(hashPassword(customer));
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error registering customer: " + e.getMessage());
        }

    }

    private Customer hashPassword(Customer customer){
        var cust_1 = customer.clone();
        cust_1.setPwd(passwordEncoder.encode(customer.getPwd()));
        return cust_1;
    }
}
