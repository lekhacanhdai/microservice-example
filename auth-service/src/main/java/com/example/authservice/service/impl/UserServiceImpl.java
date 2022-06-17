package com.example.authservice.service.impl;

import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username).orElseThrow(() ->
                new IllegalStateException("User not exists with username: " + username));
    }
}
