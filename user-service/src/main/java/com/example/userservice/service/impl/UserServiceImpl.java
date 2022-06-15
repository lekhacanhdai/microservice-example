package com.example.userservice.service.impl;

import com.example.userservice.model.Role;
import com.example.userservice.model.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.RoleService;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleService roleService;

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public UserEntity updateUser(long userId, UserEntity user) {
        UserEntity user1 = findUserById(userId);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        UserEntity userSave = userRepo.save(user1);
        return userSave;
    }

    @Override
    public UserEntity findUserById(long id) {
        return userRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("User not found with id " + id));
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepo.delete(findUserById(id));
    }

    @Override
    public UserEntity addRoleToUser(long userId, String rolename) {
        Role role = roleService.findRoleByRoleName(rolename);
        UserEntity user = findUserById(userId);
        user.getRoles().add(role);
        role.getUsers().add(user);
        saveUser(user);
        roleService.saveRole(role);
        return user;
    }
}
