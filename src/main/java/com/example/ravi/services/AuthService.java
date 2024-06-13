package com.example.ravi.services;

import com.example.ravi.models.*;
import com.example.ravi.payloads.responses.Response;
import org.springframework.http.ResponseEntity;

public abstract class AuthService {
    public abstract ResponseEntity<AuthResponse> registerUser(RegistrationRequest request);

    public abstract ResponseEntity<TokenResponse> loginUser(LoginRequest request);

    public abstract Response<String> activateAccountThroughOTP(String otp);

    public abstract Response<Boolean> forgetPassword(String phoneNumber);

    public abstract Response<User> resendOTP(String phone);
}
