package com.leonidov.cloud.controller;

import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupPage(@ModelAttribute(name = "user") User user) {
        return "signup";
    }

    /*
    Метод регистрации пользователя, он проверяет базу данных на наличия пользователя
    по данному email, если пользователя с таким email не существует, он его зарегистрирует,
    а если существует пользователь с таким email, он выведет ошибку.
     */
    @PostMapping
    public String signupUser(@Valid @ModelAttribute(name = "user") User user) {
        boolean success = userService.save(user);
        if (success) {
            return "user";
        } else {
            System.out.println("Данная электронная почта уже зарегистрирована!");
            return "signup";
        }
    }
}
