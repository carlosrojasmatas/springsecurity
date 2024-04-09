package com.sszm.controller;

import com.sszm.model.Customer;
import com.sszm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("v1/registration")
public class RegistrationController {
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        try {
            var password = customer.getPwd();
            var encodedPass = passwordEncoder.encode(password);
            customer.setPwd(encodedPass);
            return ResponseEntity.created(URI.create("/v1/registration/" + customerRepository.save(customer).getId())).body("Customer registered successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Customer registration failed");
        }


    }

}
