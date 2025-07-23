package com.streamix.user.service;

import com.streamix.common.exception.NotFoundException;
import com.streamix.common.constant.ResponseCodeConstant;
import com.streamix.user.dto.LoginRequest;
import com.streamix.user.dto.UserRegisterRequest;
import com.streamix.user.model.User;
import com.streamix.user.repository.UserRepository;
import com.streamix.user.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public void register(UserRegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new NotFoundException(ResponseCodeConstant.USER_NOT_FOUND, "User not found with email: " + email));
    }

    public String login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return jwtUtil.generateToken((UserDetails) authenticate.getPrincipal());
    }
}
