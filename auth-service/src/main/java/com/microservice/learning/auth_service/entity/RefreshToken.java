package com.microservice.learning.auth_service.entity;

import jakarta.persistence.*;

import java.time.Instant;

public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
        private Instant erpiryDate;

        @OneToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private UserCredentials userCredentials;
}
