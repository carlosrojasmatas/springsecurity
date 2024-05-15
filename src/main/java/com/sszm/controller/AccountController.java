package com.sszm.controller;

import com.sszm.model.Accounts;
import com.sszm.repository.AccountsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    private final AccountsRepository accountsRepository;

    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public List<Accounts> getAccountDetails(@RequestParam UUID customerId) {
        var r = accountsRepository.findAllByCustomerId(customerId);
        return r;
    }
}
