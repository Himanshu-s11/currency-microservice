package com.microservice.learning.auth_service.config;

import com.microservice.learning.auth_service.entity.CustomeUserDetails;
import com.microservice.learning.auth_service.entity.UserCredentials;
import com.microservice.learning.auth_service.repo.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredentials = userCredentialsRepo.findByName(username);
        return userCredentials.map(CustomeUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }


}
