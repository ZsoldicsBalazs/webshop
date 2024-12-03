package com.balazs.spring_webshop_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @RequestMapping("/users")
    public String getUser(){
        return "USERS ---> BALAZS <---";
    }
}
