package com.leonidov.cloud.service;

import com.leonidov.cloud.AuthUser;
import com.leonidov.cloud.dao.UserRepo;
import com.leonidov.cloud.model.Role;
import com.leonidov.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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
            user.setRole(Role.valueOf(Role.ROLE_USER.toString()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Optional<User> o = userRepo.getUserByUsername(username);
        System.out.println(passwordEncoder.matches(password, o.get().getPassword()));
        if (passwordEncoder.matches(password, o.get().getPassword())) {
            return o.get();
        } else {
            System.out.println("Хуита");
            throw new UsernameNotFoundException("");
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
