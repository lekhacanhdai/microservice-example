package com.example.authservice.service.impl;

import com.example.authservice.model.AuthUserDetail;
import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByUsername(username).orElseThrow(() ->
                new IllegalStateException("User not found with username: " + username));

        UserDetails userDetails = new AuthUserDetail(user.getUsername(), user.getPassword());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}