package com.dream.uniclub.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void saveFile(MultipartFile file);

    public Resource loadFile(String filename);
}
