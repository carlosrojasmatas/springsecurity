package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails(){
        return "my accounts";
    }
}
