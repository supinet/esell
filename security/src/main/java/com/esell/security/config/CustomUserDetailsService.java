package com.esell.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.esell.security.model.UserCredential;
import com.esell.security.repository.UserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found " + username));

        // System.out.println(passwordEncoder().matches("123", user.getPassword()));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
