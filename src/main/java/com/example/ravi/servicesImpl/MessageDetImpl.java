package com.example.ravi.servicesImpl;

import com.example.ravi.models.User;
import com.example.ravi.payloads.requests.MessageRequestDto;
import com.example.ravi.payloads.responses.Response;
import com.example.ravi.repository.*;
import com.example.ravi.services.MessageDetService;
import com.example.ravi.services.SmsIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageDetImpl extends MessageDetService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private SmsIntegrationService smsIntegrationService;


    @Autowired
    public MessageDetImpl(UserRepository userRepository, LoggedUser loggedUser, SmsIntegrationService smsIntegrationService) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.smsIntegrationService = smsIntegrationService;
    }

    @Override
    public User getUserByPhoneNumber(String message) {
        try {
            User user = loggedUser.getUser();
            if (user == null) {
                log.warn("UNAUTHORIZED USER TRY TO GET STATUS BY UID");
                return null;
            }
            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                return null;
            }
            log.info("Phone Number {}", phoneNumber);
//            User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
            log.info("User {}", user);

            if (user != null) {
                log.info("User found");
                ReceivedMessage receivedMessage = new ReceivedMessage();
                String phoneNumber1 = user.getPhoneNumber();
                String region = user.getRegion();
                String district = user.getDistrict();

                receivedMessage.setSource_addr("INFO");
                receivedMessage.setEncoding(0);
                receivedMessage.setMessage(
                        "Message: " + message + "\n" +
                                "Phone Number: " + phoneNumber1 + "\n" +
                                "Tax Region: " + region + "\n" +
                                "Tax District: " + district
                );
                List<Recipient> recipients = new ArrayList<>();
                recipients.add(new Recipient(1, "255782671763"));
                receivedMessage.setRecipients(recipients);
                ResponseMessage responseFromDownstream = smsIntegrationService.getResponseFromDownstream(receivedMessage);

                if (responseFromDownstream != null) {

                }
            }
            return user;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public Response<com.example.ravi.models.ResponseMessage> sendMessage(MessageRequestDto messageRequestDto) {
        return null;
    }
}
