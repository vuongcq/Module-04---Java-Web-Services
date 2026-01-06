package com.example.session08.service;

import com.example.session08.model.entity.Role;
import com.example.session08.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
