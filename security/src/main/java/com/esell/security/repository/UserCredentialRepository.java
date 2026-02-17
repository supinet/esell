package com.esell.security.repository;

import com.esell.security.model.UserCredential;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    Optional<UserCredential> findByUsername(String username);

}
