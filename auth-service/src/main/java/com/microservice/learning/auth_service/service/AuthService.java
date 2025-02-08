package com.microservice.learning.auth_service.service;

import com.microservice.learning.auth_service.dto.UserCredentialsDto;
import com.microservice.learning.auth_service.entity.UserCredentials;
import com.microservice.learning.auth_service.repo.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //save userCredentialsRepo
    public String saveUser(UserCredentials userCredentials){
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        UserCredentials userCredentials1= userCredentialsRepo.save(userCredentials);
        return "User saved with id: "+userCredentials1.getId();
    }
//generate token
//    public String generateTocken(String username, String role){
//        authenticateUser()
//        return jwtService.generateToken(username,role);
//    }
//authenticate user
    public String authenticateUser(UserCredentialsDto userCredentials){
        UserCredentials user = userCredentialsRepo.findByName(userCredentials.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userCredentials.getName(),userCredentials.getPassword()));
        if(authentication.isAuthenticated())
        {
                return jwtService.generateToken(user.getName(), user.getRole().name());
        }else {
            return "Invalid Credentials";
        }
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
