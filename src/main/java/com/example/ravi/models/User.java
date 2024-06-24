package com.example.ravi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String organisationType;

    @Column(nullable = false)
    private String organisationName;

    @JsonIgnore
    @Basic(optional = true)
    @Column(name = "token_created_at")
    private LocalDateTime tokenCreatedAt = LocalDateTime.now();

    @JsonIgnore
    @Basic(optional = true)
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Basic(optional = true)
    @Column(name = "reset_link_sent")
    private Boolean resetLinkSent = false;

    @Basic(optional = true)
    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "one_time_password")
    private String oneTimePassword;

    @Column(name = "no_of_otp_sent", columnDefinition = "integer default 0")
    private Integer noOfOtpSent = 0;

    @Column(name = "last_otp_sent_at")
    private LocalDateTime lastOtpSentAt;

    @Column(name = "phone_number_verified", nullable = false, columnDefinition = "boolean default false")
    private Boolean phoneNumberVerified = false;

    @Column(name = "account_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false, columnDefinition = "boolean default true")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;

    public void setActive(boolean b) {
        this.enabled = b;
    }
}
