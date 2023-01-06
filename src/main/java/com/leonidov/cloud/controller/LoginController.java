package com.leonidov.cloud.controller;

import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    /*
    Метод авторизации пользователя, он проверяет базу данных на наличия пользователя
    по данному email, если пользователя с таким email не существует, он выведет ошибку,
    а если существует пользователь с таким email, он проверит пароль, если он правильный, он его авторизует.
     */
    /*@PostMapping
    public String loginUser(@RequestParam String username,
                            @RequestParam String password) {

        User user = userService.getUserByUsernameAndPassword(username, password);

        if (user == null) {
            System.out.println("non success");
            return "login";
        } else {
            System.out.println("success");
            userService.loadUserByUsername(username);
            return "user";
         }
    }*/
}
