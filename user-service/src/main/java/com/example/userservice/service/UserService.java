package com.example.userservice.service;

import com.example.userservice.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(long userId, UserEntity user);

    UserEntity myUpdateUser(long userId, UserEntity user);

    UserEntity findUserById(long id);

    List<UserEntity> getAllUser();

    void deleteUser(long id);

    UserEntity addRoleToUser(long userId, String RoleName);
}
