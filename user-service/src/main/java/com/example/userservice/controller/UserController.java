package com.example.userservice.controller;

import com.example.userservice.model.UserEntity;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HttpMethod;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RestController
@AllArgsConstructor
@Api(tags = "users", value = "UserController")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Get list user on the system", response = List.class, tags = "users", httpMethod = HttpMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/users")
    private ResponseEntity<List<UserEntity>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }



    @ApiOperation(value = "Add new user", response = UserEntity.class, tags = "users", httpMethod = HttpMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping("/users")
    public ResponseEntity<UserEntity> register(
            @ApiParam(
            name = "User model",
            type = "UserEntity",
            value = "Model of the user",
            required = true
    )
            @RequestBody UserEntity userEntity){
        UserEntity user = userService.saveUser(userEntity);
        UserEntity userRes = userService.addRoleToUser(user.getId(), "ROLE_USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
    }



    @ApiOperation(
            value = "Get user by id",
            response = UserEntity.class, tags = "users",
            httpMethod = HttpMethod.GET
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(
            @ApiParam(
                    name = "Id",
                    value = "Id of the user"
            )
            @PathVariable long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }



    @ApiOperation(
            value = "Update user",
            response = UserEntity.class, tags = "users",
            httpMethod = HttpMethod.PUT
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(
            @ApiParam(
                    name = "Id",
                    value = "Id of the user"
            )
            @PathVariable long id,
            @ApiParam(
                    name = "User model",
                    type = "UserEntity",
                    value = "Model of the user",
                    required = true
            )
            @RequestBody UserEntity userEntity
    ){
        return ResponseEntity.ok(userService.updateUser(id, userEntity));
    }

    

    @ApiOperation(value = "Update user", tags = "users", httpMethod = HttpMethod.DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Not authorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(
            @ApiParam(
                    name = "Id",
                    value = "Id of the user"
            )
            @PathVariable long id
    ){
        userService.deleteUser(id);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(res);
    }


    @PutMapping("/myupdate/{id}")
    public ResponseEntity<UserEntity> myUpdate(@PathVariable long id, @RequestBody UserEntity user){
        UserEntity response = userService.updateUser(id, user);
        return ResponseEntity.ok(response);
    }

}
