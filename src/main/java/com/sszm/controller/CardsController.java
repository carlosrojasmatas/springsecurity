package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cards")
public class CardsController {


    @GetMapping("myCards") // Base path for all endpoints
    public String getCards(){
        return "my cards";
    }

}
