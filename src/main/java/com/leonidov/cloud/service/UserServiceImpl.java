package com.leonidov.cloud.service;

import com.leonidov.cloud.AuthUser;
import com.leonidov.cloud.dao.UserRepo;
import com.leonidov.cloud.model.Role;
import com.leonidov.cloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

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
    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public boolean save(User user) {
        Optional<User> o = userRepo.getUserByEmail(user.getEmail());

        if (o.isPresent()) {
            return false;
        } else {
            user.setRole(Role.valueOf(Role.ROLE_USER.toString()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.getUserByEmail(email);
        if (user.isPresent()) {
            return new AuthUser(user.get());
        } else {
            throw new UsernameNotFoundException(format("Пользователь: %s, не найден", email));
        }
    }
}
