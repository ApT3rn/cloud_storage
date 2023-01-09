package com.leonidov.cloud.service;

import com.leonidov.cloud.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    Optional<User> getUserByUsername(String username);
    public boolean save(User user);
    public User getUserByUsernameAndPassword(String username, String password);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
