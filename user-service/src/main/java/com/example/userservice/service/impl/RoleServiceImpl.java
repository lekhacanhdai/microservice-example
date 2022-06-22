package com.example.userservice.service.impl;

import com.example.userservice.model.Role;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;

    @Override
    public Role findRoleByRoleName(String name) {
        return roleRepo.findRoleByName(name).orElseThrow(() ->
                new IllegalStateException("Role not exist with name "+ name));
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }
}
