package com.example.ravi.services;

import com.example.ravi.models.User;

public interface UserService {
    User getUserByPhoneNumber(String phoneNumber);
}
