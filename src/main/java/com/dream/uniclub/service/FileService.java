package com.dream.uniclub.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void saveFile(MultipartFile file);
}
