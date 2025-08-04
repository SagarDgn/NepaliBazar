package com.nepalibazar.service;

import jakarta.inject.Singleton;

@Singleton
public class EmailValidationService {

    public boolean isGoogleEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@gmail.com");
    }
}

