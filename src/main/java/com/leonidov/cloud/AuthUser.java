package com.leonidov.cloud;

import com.leonidov.cloud.model.User;

import javax.validation.constraints.NotNull;
import java.util.Collections;

public class AuthUser extends org.springframework.security.core.userdetails.User {
    @NotNull
    private User user;

    public AuthUser (@NotNull User user) {
        super(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole()));
        this.user = user;
    }

    public Long id () {
        return user.getId();
    }
}
