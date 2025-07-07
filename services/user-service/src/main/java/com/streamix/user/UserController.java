package com.streamix.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from User Service!";
    }

    @GetMapping("/info")
    public String info() {
        return "User Service is running on port 8081";
    }
} 