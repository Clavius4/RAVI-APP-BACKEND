package com.example.ravi.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_category")
    private String message_category;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "status")
    private Long status;

    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "message_text", nullable = false)
    private String messageText;

    @Column(name = "sent_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime sentAt;
}

