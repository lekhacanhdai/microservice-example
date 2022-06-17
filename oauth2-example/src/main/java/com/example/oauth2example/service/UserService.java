package com.example.oauth2example.service;

import com.example.oauth2example.model.Provider;
import com.example.oauth2example.model.User;
import com.example.oauth2example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void proccessOAuthPostLogin(String username){
        boolean exitsUser = userRepo.findUserByUsername(username).isPresent();
        if (exitsUser == false){
            User user = new User();
            user.setUsername(username);
            user.setProvider(Provider.GOOGOLE);
            userRepo.save(user);
        }
    }
}
