package com.example.ravi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String organisationName;
    private String organisationType;
    private String district;
    private String region;


    public TokenResponse(String message) {
        this.access_token = message;
    }


}

