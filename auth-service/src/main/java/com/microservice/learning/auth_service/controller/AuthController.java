package com.microservice.learning.auth_service.controller;


import com.microservice.learning.auth_service.dto.UserCredentialsDto;
import com.microservice.learning.auth_service.entity.UserCredentials;
import com.microservice.learning.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserCredentials userCredentials){
        return ResponseEntity.status(HttpStatus.OK).body(authService.saveUser(userCredentials));
    }

    @PostMapping("/token")
    public String authenTicateAndGenerateTocken(@RequestBody UserCredentialsDto userCredentials){
            return authService.authenticateUser(userCredentials);

        }

    @GetMapping("/validate")
    public String validTocken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
