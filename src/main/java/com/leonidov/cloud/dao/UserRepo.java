package com.leonidov.cloud.dao;

import com.leonidov.cloud.model.Role;
import com.leonidov.cloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> getUserByUsername(String username);
    //public User getUserByUsernameAndPassword(String username, String password);
}
