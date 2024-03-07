package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/balances")
public class BalanceController {

    @GetMapping("myBalance")
    public String myBalance(){
        return "my balances";
    }
}
