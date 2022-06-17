package com.example.oauth2securityexample.service;

import com.example.oauth2securityexample.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUser();

    UserEntity saveNewUser(UserEntity user);
}
