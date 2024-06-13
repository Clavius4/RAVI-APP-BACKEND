package com.example.ravi.models;

import java.util.List;

public class MessageRequest {
    private String source_addr;
    private String schedule_time;
    private String encoding;
    private String message;
    private List<Recipient> recipients;

    // Getters and Setters

    public static class Recipient {
        private int recipient_id;
        private String dest_addr;

        // Getters and Setters
    }
}
