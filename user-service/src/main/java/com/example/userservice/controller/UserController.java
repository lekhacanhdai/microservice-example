package com.example.userservice.controller;

import com.example.userservice.model.UserEntity;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity){
        UserEntity user = userService.saveUser(userEntity);
        UserEntity userRes = userService.addRoleToUser(user.getId(), "ROLE_USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.updateUser(id, userEntity));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(res);
    }

}
