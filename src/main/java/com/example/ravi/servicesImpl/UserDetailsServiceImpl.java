package com.example.ravi.servicesImpl;

import com.example.ravi.models.User;
import com.example.ravi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("A user with this phone number does not exist!"));
        return UserDetailsImpl.build(user);
    }
}
