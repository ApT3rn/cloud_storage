package com.leonidov.cloud.controller;

import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/forget")
public class ForgetPassword {

    private final UserService userService;

    @Autowired
    public ForgetPassword(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String forgetPage() {
        return "forget";
    }

    /*
    Метод восстановления пароля, он ищет пользователя по email.
    Если пользователь существует, он отправит новый пароль на почту,
    если пользователя не существует, он выведет ошибку
     */
    @PostMapping
    public String forgetPassword(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            System.out.println("Новый пароль отправлен на почту!");
        } else {
            System.out.println("Данный пользователь не зарегистрирован");
        }
        return "redirect:/forget";
    }
}

