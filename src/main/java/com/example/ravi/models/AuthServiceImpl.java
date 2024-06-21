package com.example.ravi.models;

import com.example.ravi.jwt.JWTUtils;
import com.example.ravi.payloads.requests.MessageRequestDto;
import com.example.ravi.payloads.responses.Response;
import com.example.ravi.repository.UserRepository;
import com.example.ravi.services.AuthService;
import com.example.ravi.services.SmsIntegrationService;
import com.example.ravi.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class AuthServiceImpl extends AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsIntegrationService smsIntegrationService;

    @Override
    public ResponseEntity<AuthResponse> registerUser(RegistrationRequest request) {
        if (request.getPhoneNumber() == null || request.getPassword() == null ||
                request.getFirstName() == null || request.getLastName() == null ||
                request.getRegion() == null || request.getDistrict() == null ||
                request.getOrganisationType() == null || request.getOrganisationName() == null) {
            return new ResponseEntity<>(new AuthResponse("Invalid input data"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            return new ResponseEntity<>(new AuthResponse("Phone number already in use"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRegion(request.getRegion());
        user.setDistrict(request.getDistrict());
        user.setOrganisationType(request.getOrganisationType());
        user.setOrganisationName(request.getOrganisationName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Generate OTP
        String otp = generateOTP();
        user.setOneTimePassword(otp);
        user.setLastOtpSentAt(LocalDateTime.now());
        String phone = request.getPhoneNumber();

        userRepository.save(user);
        log.info("User saved");

        // Send OTP via SMS
        ReceivedMessage receivedMessage = new ReceivedMessage();
        receivedMessage.setEncoding(0);
        receivedMessage.setMessage("Your verification code is " + otp + ", use this to activate your phone number");
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient(1, phone));
        ReceivedMessage.setRecipients(recipients);
        receivedMessage.setSource_addr("INFO");
        log.info("OTP {}", otp);

        Response<ResponseMessage> responseMessageResponse = smsIntegrationService.sendMessage(receivedMessage);
        if (responseMessageResponse.getCode() != ResponseCode.SUCCESS) {
            log.info(String.valueOf(responseMessageResponse.getCode()));
        } else {
            log.info(String.valueOf(responseMessageResponse.getCode()));
        }
        log.info("OTP sent");

        // Automatically activate the account using the generated OTP
//        Response<String> activationResponse = activateAccountThroughOTP(otp);
//        if (activationResponse.getCode() != ResponseCode.SUCCESS) {
//            return new ResponseEntity<>(new AuthResponse("User registered but failed to activate account. Please check your OTP."), HttpStatus.CREATED);
//        }

        return new ResponseEntity<>(new AuthResponse("User registered and activated successfully. OTP sent to your phone number."), HttpStatus.CREATED);
    }

    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(999999);
        return String.format("%06d", otp); // 6-digit OTP
    }


    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginRequest request) {
        if (request.getPhoneNumber() == null || request.getPassword() == null) {
            return new ResponseEntity<>(new TokenResponse("Invalid input data"), HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(new TokenResponse("User not found"), HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(new TokenResponse("Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        String phoneNumber = user.getPhoneNumber();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String region = user.getRegion();
        String district = user.getDistrict();
        String organisationType = user.getOrganisationType();
        String organisationName = user.getOrganisationName();
        // Generate JWT tokens using JWTUtils
        String accessToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = UUID.randomUUID().toString();  // Implement refresh token logic if needed

        TokenResponse tokenResponse = new TokenResponse(accessToken, "bearer", refreshToken, 3600, "read write",phoneNumber,firstName,lastName,region,district,organisationType,organisationName);

        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @Override
    public Response<String> activateAccountThroughOTP(String otp) {
        log.info("OTP activated");
        try {
            Optional<User> firstByOneTimePassword = userRepository.findFirstByOneTimePassword(otp);
            if (firstByOneTimePassword.isEmpty()) {
                return new Response<>(true, ResponseCode.DOES_NOT_EXIST, "Invalid OTP, could not find account associated with this code");
            }
            User user = firstByOneTimePassword.get();
            user.setActive(true);
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setEnabled(true);
            user.setPhoneNumberVerified(true);
            userRepository.save(user);
            return new Response<>(false, ResponseCode.SUCCESS, "Your account has been activated, please login to continue");
        } catch (Exception e) {
            log.error("EXCEPTION OCCURRED", e);
        }
        return new Response<>(true, ResponseCode.FAIL, "Failed to activate your account");
    }

    @Override
    public Response<Boolean> forgetPassword(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            return new Response<>(true, ResponseCode.DOES_NOT_EXIST, "Invalid phone number format");
        }

        Optional<User> userOptional = userRepository.findFirstByPhoneNumber(phoneNumber);
        if (userOptional.isEmpty()) {
            return new Response<>(true, ResponseCode.DOES_NOT_EXIST, "User not found");
        }

        User user = userOptional.get();

        // Generate reset token
        String resetToken = generateResetToken();

        // Store the reset token and its expiration time
        user.setOneTimePassword(resetToken);
        user.setLastOtpSentAt(LocalDateTime.now());
        userRepository.save(user);

        // Send the reset token via SMS
        ReceivedMessage receivedMessage = new ReceivedMessage();
        receivedMessage.setEncoding(0);
        receivedMessage.setMessage("Your password reset token is: " + resetToken);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(new Recipient(1, phoneNumber));
        ReceivedMessage.setRecipients(recipients);
        receivedMessage.setSource_addr("INFO");

        smsIntegrationService.sendMessage(receivedMessage);

        return new Response<>(false, ResponseCode.SUCCESS, "Password reset token sent successfully");
    }

    private boolean isValidPhoneNumber(String phoneNumberStr) {
        Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^\\+?[1-9]\\d{1,14}$");
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumberStr);
        return matcher.find();
    }

    private String generateResetToken() {
        SecureRandom random = new SecureRandom();
        int token = random.nextInt(999999);
        return String.format("%06d", token); // 6-digit token
    }

    @Override
    public Response<User> resendOTP(String phone) {
        try {
            if (phone == null || phone.isBlank())
                return new Response<>(true, ResponseCode.NULL_ARGUMENT, "PhoneNumber can not be empty");

            phone = phone.trim();
            if (phone.startsWith("+")) {
                phone = phone.substring(1); // Remove the leading '+'
            }

            Optional<User> userOptional = userRepository.findFirstByPhoneNumber(phone);
            if (userOptional.isEmpty())
                return new Response<>(true, ResponseCode.DOES_NOT_EXIST, "PhoneNumber does not exist");

            User user = userOptional.get();

            if (user.getPhoneNumberVerified())
                return new Response<>(true, ResponseCode.FAIL, "PhoneNumber is already verified");

            // Define your delay multiplier (e.g., each subsequent resend request waits longer)
            int delayMultiplier = 4; // Set delay multiplier to 4

            // Calculate the waiting time before the next OTP can be sent
            long waitTimeSeconds;
            if (user.getNoOfOtpSent() == 1) {
                // For the first resend attempt, wait for 30 seconds
                waitTimeSeconds = 30;
            } else {
                // Calculate the waiting time based on the delay multiplier and previous attempts
                waitTimeSeconds = (long) (30 * Math.pow(delayMultiplier, user.getNoOfOtpSent() - 1));
            }

            // Calculate the time difference since the last OTP was sent
            LocalDateTime currentTime = LocalDateTime.now();
            Duration timeElapsed = Duration.between(user.getLastOtpSentAt(), currentTime);

            // Adjust wait time based on elapsed time
            waitTimeSeconds -= timeElapsed.getSeconds();

            if (waitTimeSeconds > 0) {
                // Return response indicating the user needs to wait
                return new Response<>(true, ResponseCode.WAIT, "Please wait for " + waitTimeSeconds + " seconds before resending OTP");
            }

            Random random = new SecureRandom();
            int nextInt = random.nextInt(999999);

            ReceivedMessage receivedMessage = new ReceivedMessage();
            receivedMessage.setEncoding(0);
            receivedMessage.setMessage("Your verification code is " + nextInt + ", use this to activate your phoneNumber");
            List<Recipient> recipients = new ArrayList<>();
            recipients.add(new Recipient(1, phone));
            ReceivedMessage.setRecipients(recipients);
            receivedMessage.setSource_addr("INFO");

            user.setOneTimePassword(String.valueOf(nextInt));

            Response<ResponseMessage> responseMessageResponse = smsIntegrationService.sendMessage(receivedMessage);

            if (responseMessageResponse.getCode() == ResponseCode.SUCCESS) {
                user.setNoOfOtpSent(user.getNoOfOtpSent() + 1);
                user.setLastOtpSentAt(LocalDateTime.now());

                userRepository.save(user);

                return new Response<>(false, ResponseCode.SUCCESS, "Record created successfully");
            }
            return new Response<>(true, ResponseCode.FAIL, "Failed to resend OTP, unknown error has occurred");

        } catch (Exception e) {
            log.error("EXCEPTION OCCURRED", e);
            return new Response<>(true, ResponseCode.FAIL, "Failed to resend OTP, unknown error has occurred");
        }
    }

    public Boolean verifyTin(String tin) {
        return tin != null && tin.matches("\\d{11}");
    }
}
