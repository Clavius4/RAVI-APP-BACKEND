package com.example.ravi.payloads.requests;

import com.example.ravi.payloads.responses.Recipient;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDto {
    private static final long serialVersionUID = 1L;
    private String source_addr;
    private String phoneNumber;
    private Integer encoding;
    private String message;
    private List<Recipient> recipients;
}
