package com.example.ravi.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String phoneNumber;

}
