package com.example.ravi.models;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String region;
    private String district;
    private String organisationType;
    private String organisationName;

}

