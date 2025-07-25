package com.streamix.user.service;

import com.streamix.common.exception.NotFoundException;
import com.streamix.user.constant.ResponseCode;
import com.streamix.user.model.Role;
import com.streamix.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Role not found with: " + name, ResponseCode.ROLE_NOT_FOUND));
    }
}
