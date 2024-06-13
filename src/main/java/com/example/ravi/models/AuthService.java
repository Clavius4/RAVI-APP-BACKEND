package com.example.ravi.models;

import com.example.ravi.models.AuthResponse;
import com.example.ravi.models.LoginRequest;
import com.example.ravi.models.RegistrationRequest;
import com.example.ravi.models.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> registerUser(RegistrationRequest request);
    ResponseEntity<TokenResponse> loginUser(LoginRequest request);

}
