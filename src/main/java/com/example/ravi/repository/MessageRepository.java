//package com.example.ravi.repository;
//
//// MessageRepository.java
//
//import com.example.ravi.models.MessageRequest;
//import com.example.ravi.payloads.responses.MessageResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.client.RestTemplate;
//
//@Repository
//public class MessageRepository {
//    @Value("${message.endpoint}")
//    private String messageEndpoint;
//
//    private final RestTemplate restTemplate;
//
//    public MessageRepository(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ResponseEntity<MessageResponse> sendMessage(MessageRequest request) {
//        return restTemplate.postForEntity(messageEndpoint, request, MessageResponse.class);
//    }
//}
//
