package com.example.ravi.models;


import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}

