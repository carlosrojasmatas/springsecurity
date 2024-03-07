package com.sszm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/notices")
public class NoticesController {


    @GetMapping("myNotices") // Base path for all endpoints
    public String getNotices(){
        return "my notices";
    }

}
