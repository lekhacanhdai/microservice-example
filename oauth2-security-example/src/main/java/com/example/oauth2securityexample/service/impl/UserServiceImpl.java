package com.example.oauth2securityexample.service.impl;

import com.example.oauth2securityexample.model.Role;
import com.example.oauth2securityexample.model.UserEntity;
import com.example.oauth2securityexample.repository.RoleRepository;
import com.example.oauth2securityexample.repository.UserRepository;
import com.example.oauth2securityexample.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity saveNewUser(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1L).orElseThrow(() ->
                new IllegalStateException("Role not found"));
        userEntity.getRoles().add(role);
        role.getUsers().add(userEntity);
        userRepository.save(userEntity);
        roleRepository.save(role);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByUsername(username).orElseThrow(() ->
                new IllegalStateException("User not found with username: " + username));
        Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        user.getRoles().forEach(role ->
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
