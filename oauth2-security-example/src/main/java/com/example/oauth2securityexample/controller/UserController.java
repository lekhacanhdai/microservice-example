package com.example.oauth2securityexample.controller;

import com.example.oauth2securityexample.model.UserEntity;
import com.example.oauth2securityexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    
    @PostMapping("/registration")
    public ResponseEntity<UserEntity> registration(@RequestBody UserEntity user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveNewUser(user));
    }
}
