package com.microservice.learning.auth_service.repo;

import com.microservice.learning.auth_service.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByName(String name);
}
