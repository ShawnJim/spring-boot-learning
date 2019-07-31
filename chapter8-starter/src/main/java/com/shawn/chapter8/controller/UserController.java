package com.shawn.chapter8.controller;

import com.shawn.mystarter.mystarter.service.UserSevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @GetMapping(value = "/user")
    public String getUser(){
        return UserSevice.getUser().toString();
    }
}
