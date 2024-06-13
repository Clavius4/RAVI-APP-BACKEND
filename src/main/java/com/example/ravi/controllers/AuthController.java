package com.example.ravi.controllers;

import com.example.ravi.models.*;
import com.example.ravi.payloads.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegistrationRequest request) throws ServiceNotFoundException {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }


    @RequestMapping(path = "/user/forget-password", method = RequestMethod.POST)
    public ResponseEntity<?> forgetPassword(@RequestParam(value = "phoneNumber", required = true)String phoneNumber){
        Response<Boolean> forgetPassword = authService.forgetPassword(phoneNumber);
        return ResponseEntity.ok()
                .body(forgetPassword);
    }


    @PostMapping(path = "/activate-account")
    public ResponseEntity<?> activateAccount(@RequestParam(value = "code")String code) {
        Response<String> stringResponse = authService.activateAccountThroughOTP(code);
        return ResponseEntity.ok()
                .body(stringResponse);
    }


    @PostMapping("/resend_otp/{phone}")
    public ResponseEntity<?> resendOTP(@PathVariable String phone) {
        return ResponseEntity.ok()
                .body(authService.resendOTP(phone));
    }


}
