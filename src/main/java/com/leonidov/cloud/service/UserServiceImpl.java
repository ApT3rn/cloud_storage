package com.leonidov.cloud.service;

import com.leonidov.cloud.AuthUser;
import com.leonidov.cloud.dao.UserRepo;
import com.leonidov.cloud.model.Role;
import com.leonidov.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public boolean save(User user) {
        Optional<User> userFromDB = userRepo.getUserByUsername(user.getUsername());

        if (userFromDB.isPresent()) {
            return false;
        } else {
            user.setRole(Role.valueOf(Role.USER.toString()));
            userRepo.save(user);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByUsername(username);
        if (user.isPresent()) {
            return new AuthUser(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
