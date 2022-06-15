package com.example.userservice.service;

import com.example.userservice.model.Role;

public interface RoleService {
    Role findRoleByRoleName(String name);
    Role saveRole(Role role);
}
