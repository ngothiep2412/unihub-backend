package com.dream.uniclub.controller;

import com.dream.uniclub.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    FileService fileService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestParam MultipartFile file) {
        System.out.println("Kiem tra" + file.getOriginalFilename());
        fileService.saveFile(file);

        return new ResponseEntity<>("Hello add product", HttpStatus.OK);
    }
}
