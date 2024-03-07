package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/contact")
public class ContactController {


    @GetMapping("details") // Base path for all endpoints
    public String getContactDetails(){
        return "contact details";
    }

}
