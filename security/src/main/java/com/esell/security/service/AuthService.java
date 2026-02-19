package com.esell.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esell.security.model.UserCredential;
import com.esell.security.repository.UserCredentialRepository;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user add to the system";
    }

    public String generateToken(String userName) {
        List<String> roles = new ArrayList<>();
        return jwtService.generateToken(userName, roles);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
