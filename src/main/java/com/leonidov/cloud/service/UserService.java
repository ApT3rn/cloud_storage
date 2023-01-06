package com.leonidov.cloud.service;

import com.leonidov.cloud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);
    public boolean save(User user);
}
