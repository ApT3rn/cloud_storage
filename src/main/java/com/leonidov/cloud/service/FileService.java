package com.leonidov.cloud.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface FileService {
    public void createMainFolder();
    public void createUserFolder(String email);
    public List<File> allFiles(String email);
    public String getUserFolder (String email);
}
