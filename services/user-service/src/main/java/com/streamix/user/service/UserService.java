package com.streamix.user.service;

import com.streamix.common.constant.ResponseCodeConstant;
import com.streamix.common.exception.NotFoundException;
import com.streamix.user.converter.UserConverter;
import com.streamix.user.dto.LoginRequest;
import com.streamix.user.dto.LoginResponse;
import com.streamix.user.dto.UserRegisterRequest;
import com.streamix.user.dto.UserResponse;
import com.streamix.user.model.Role;
import com.streamix.user.model.User;
import com.streamix.user.repository.UserRepository;
import com.streamix.user.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, RoleService roleService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
    }

    public UserResponse register(UserRegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        Role role = roleService.findByName("USER");
        user.setRoles(List.of(role));
        repository.save(user);
        log.info(" User registered with email: {}", user.getEmail());
        return UserConverter.toResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        String refreshToken = jwtUtil.generateRefreshToken((UserDetails) authentication.getPrincipal());
        log.info("User logged in with email: {}", request.getEmail());
        return UserConverter.toLoginResponse(token, refreshToken);
    }

    public User findByEmail(String email) {
        return repository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email, ResponseCodeConstant.USER_NOT_FOUND));
    }
}
