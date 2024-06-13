package com.example.ravi.repository;

import com.example.ravi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findFirstByPhoneNumber(String phone);

    Optional<User> findFirstByOneTimePassword(String otp);
}
