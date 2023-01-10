package com.leonidov.cloud.controller;

import com.leonidov.cloud.Mediator;
import com.leonidov.cloud.model.User;
import com.leonidov.cloud.service.FileService;
import com.leonidov.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    Mediator mediator = new Mediator();
    private final User user = mediator.getUser();

    private final FileService fileService;
    private final UserService userService;

    @Autowired
    public UserController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Model model) {
        model.addAttribute("user", user);
        //List<File> allFiles = fileService.allFiles(user.getEmail());
        //System.out.println(allFiles.toString());
        return "user";
    }
}
