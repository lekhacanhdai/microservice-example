package com.example.authservice.service;

import com.example.authservice.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();

    UserEntity findByUsername(String username);
}
