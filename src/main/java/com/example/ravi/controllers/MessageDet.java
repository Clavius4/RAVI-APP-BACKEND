package com.example.ravi.controllers;

import com.example.ravi.models.User;
import com.example.ravi.servicesImpl.MessageDetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
public class MessageDet {

    private static final Logger log = LoggerFactory.getLogger(MessageDet.class);
    @Autowired
    private MessageDetImpl messageDetImpl;

    @GetMapping("/user")
    public ResponseEntity<User> getUserDetails(@RequestParam String message) {

        User user = messageDetImpl.getUserByPhoneNumber(message);

        log.info("User 2 {}", user);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
