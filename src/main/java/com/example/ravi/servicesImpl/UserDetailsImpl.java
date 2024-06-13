package com.example.ravi.servicesImpl;

import com.example.ravi.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String phoneNumber;
    @JsonIgnore
    private final String password;
    private final boolean active;

    public UserDetailsImpl(Long id, String phoneNumber, String password, boolean active) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.active = active;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getPhoneNumber(),
                user.getPassword(),
                true // Assuming user is active if found
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming no specific authorities
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
