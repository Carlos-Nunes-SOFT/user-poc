package com.user.micro.demo.controller;

import com.user.micro.demo.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
