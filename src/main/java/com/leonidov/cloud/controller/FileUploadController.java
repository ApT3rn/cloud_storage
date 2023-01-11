package com.leonidov.cloud.controller;

import com.leonidov.cloud.Mediator;
import com.leonidov.cloud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    @Autowired
    FileService fileService;
    private Mediator mediator = new Mediator();
    // Сохранить загруженный файл в эту папку
    private String UPLOADED_FOLDER;

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        UPLOADED_FOLDER = fileService.getUserFolder(mediator.getUser().getEmail());

        if (file.isEmpty()) {
            return "redirect:/user";
        }
        try {
// Получить файл и сохранить его где-нибудь
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user";
    }

}
