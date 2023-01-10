package com.leonidov.cloud;

import com.leonidov.cloud.model.User;

public class Mediator {

    private User user;

    public Mediator() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
