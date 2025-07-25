package com.streamix.user.controller;

import com.streamix.common.util.ApiResponse;
import com.streamix.user.dto.LoginRequest;
import com.streamix.user.dto.LoginResponse;
import com.streamix.user.dto.UserRegisterRequest;
import com.streamix.user.dto.UserResponse;
import com.streamix.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity.ok(ApiResponse.success(service.register(request), "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success(service.login(request), "Successfully Login"));
    }

    @GetMapping("/private-test")
    @PreAuthorize(" hasRole('ROLE_USER') ")
    public void privateTest(Authentication authentication) {
        String username = authentication.getName();
        System.out.println("Private test accessed by user: " + username);
    }

}