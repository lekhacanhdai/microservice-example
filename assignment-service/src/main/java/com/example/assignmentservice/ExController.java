package com.example.assignmentservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExController {
    @Autowired
    private RestTemplate restTemplate;

    String getStudentUrl = "http://localhost:8080/users/";

    @GetMapping("/assignments/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        ResponseEntity<UserEntity> response = restTemplate.getForEntity(getStudentUrl + id, UserEntity.class);
        return response;
    }

    @PostMapping("/assignments")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        UserEntity userEntity = restTemplate.postForObject(getStudentUrl, user, UserEntity.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserEntity user){
        Object userEntity = restTemplate.execute(getStudentUrl + id, HttpMethod.PUT, requestCallback(user), HttpInputMessage::getBody);
        return ResponseEntity.ok(userEntity);
    }

    private RequestCallback requestCallback(final UserEntity updateUser){
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updateUser);
            clientHttpRequest.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, "Basic " + "ksjadfkjaskd");
        };
    }

    @DeleteMapping("/assignments/{id}")
    public void deleteUser(@PathVariable Long id){
        restTemplate.delete(getStudentUrl + id);
    }
}
