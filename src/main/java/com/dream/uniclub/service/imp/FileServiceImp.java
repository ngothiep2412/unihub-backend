package com.dream.uniclub.service.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dream.uniclub.exception.SaveFileException;
import com.dream.uniclub.service.FileService;

@Service
public class FileServiceImp implements FileService {

    @Value("${root.path}")
    private String root;

    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path rootPath = Paths.get(root);
            if (!Files.exists(rootPath)) {
                Files.createDirectory(rootPath);
            }
            Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new SaveFileException(e.getMessage());
        }

    }
}
