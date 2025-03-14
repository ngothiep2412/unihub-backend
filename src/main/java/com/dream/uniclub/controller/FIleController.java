package com.dream.uniclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.uniclub.service.FileService;

@RestController
@RequestMapping("/file")
public class FIleController {

    @Autowired
    FileService fileService;

    @GetMapping("/{filename}")
    public ResponseEntity<?> getMethodName(@PathVariable String filename) {
        Resource resource = fileService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

}
