package com.streamix.user.service;

import com.streamix.user.dto.UserRegisterRequest;
import com.streamix.common.exception.NotFoundException;
import com.streamix.user.model.User;
import com.streamix.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email, HttpStatus.NO_CONTENT));
    }
}
