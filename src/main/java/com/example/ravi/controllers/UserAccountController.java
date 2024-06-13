//package com.example.ravi.controllers;
//
//import com.example.ravi.models.User;
//import com.example.ravi.repository.UserRepository;
//import io.leangen.graphql.annotations.GraphQLArgument;
//import io.leangen.graphql.annotations.GraphQLMutation;
//import io.leangen.graphql.annotations.GraphQLQuery;
//import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//@GraphQLApi
//public class UserAccountController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GraphQLMutation(name = "registerUser")
//    public AuthResponse registerUser(@GraphQLArgument(name = "request") RegistrationRequest request) {
//        if (request.getPhoneNumber() == null || request.getPassword() == null ||
//                request.getFirstName() == null || request.getLastName() == null ||
//                request.getRegionDistrict() == null || request.getOrganisationType() == null ||
//                request.getOrganisationName() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input data");
//        }
//
//        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number already in use");
//        }
//
//        User user = new User();
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setRegionDistrict(request.getRegionDistrict());
//        user.setOrganisationType(request.getOrganisationType());
//        user.setOrganisationName(request.getOrganisationName());
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
//
//        userRepository.save(user);
//
//        return new AuthResponse("User registered successfully");
//    }
//
//    @GraphQLMutation(name = "loginUser")
//    public AuthResponse loginUser(@GraphQLArgument(name = "request") LoginRequest request) {
//        if (request.getPhoneNumber() == null || request.getPassword() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input data");
//        }
//
//        Optional<User> userOptional = userRepository.findByPhoneNumber(request.getPhoneNumber());
//
//        if (userOptional.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        }
//
//        User user = userOptional.get();
//
//        if (!request.getPassword().equals(user.getPassword())) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//        }
//
//        user.setUpdatedAt(LocalDateTime.now());
//        userRepository.save(user);
//
//        return new AuthResponse("User logged in successfully");
//    }
//
//    @Data
//    public static class RegistrationRequest {
//        private String phoneNumber;
//        private String password;
//        private String firstName;
//        private String lastName;
//        private String regionDistrict;
//        private String organisationType;
//        private String organisationName;
//    }
//
//    @Data
//    public static class LoginRequest {
//        private String phoneNumber;
//        private String password;
//    }
//
//    @Data
//    public static class AuthResponse {
//        private String message;
//
//        public AuthResponse(String message) {
//            this.message = message;
//        }
//    }
//}
