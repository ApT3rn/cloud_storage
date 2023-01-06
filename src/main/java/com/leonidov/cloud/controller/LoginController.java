package com.leonidov.cloud.controller;

import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;



    @GetMapping
    public String loginPage() {
        return "login";
    }

    /*
    Метод авторизации пользователя, он проверяет базу данных на наличия пользователя
    по данному email, если пользователя с таким email не существует, он выведет ошибку,
    а если существует пользователь с таким email, он проверит пароль, если он правильный, он его авторизует.
     */
    @PostMapping
    public String loginUser(@RequestParam String username,
                            @RequestParam String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.get().getPassword().equals(password)) {
            userDetailsService.loadUserByUsername(username);
            return "user";
        } else {
            throw new UsernameNotFoundException("Password wrong");
        }
    }
}
