package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/loans")
public class LoansController {


    @GetMapping("myLoans") // Base path for all endpoints
    public String getLoans(){
        return "my loans";
        // Controller methods and logic defined here
    }

}
