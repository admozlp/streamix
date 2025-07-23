package com.streamix.user.controller;

import com.streamix.user.dto.LoginRequest;
import com.streamix.user.dto.UserRegisterRequest;
import com.streamix.user.service.UserService;
import com.streamix.user.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody @Valid UserRegisterRequest request) {
        service.register(request);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody @Valid LoginRequest request) {
        String token = service.login(request);
        return ResponseEntity.ok(ApiResponse.success(token, "Login successful"));
    }

}