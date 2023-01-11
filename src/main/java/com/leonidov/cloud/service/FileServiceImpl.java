package com.leonidov.cloud.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private static final File path = new File(new File("").getAbsolutePath() + "/all_users");
    //System.out.println(path.getAbsolutePath());

    public FileServiceImpl() {
    }

    @Override
    public void createMainFolder() {
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    @Override
    public void createUserFolder(String email) {
        File paths = new File(path + "/" + email);
        paths.mkdirs();
    }

    @Override
    public List<File> allFiles(String email) {
        List<File> filesInFolder = null;
        try {
            filesInFolder = Files.walk(Paths.get(path + "/" + email + "/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filesInFolder;
    }

    @Override
    public String getUserFolder(String email) {
        return new String(path.toString() + "/" + email + "/");
    }
}
