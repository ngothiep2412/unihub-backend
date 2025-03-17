package com.dream.uniclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.uniclub.entity.CategoryEntity;
import com.dream.uniclub.response.BaseResponse;
import com.dream.uniclub.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<CategoryEntity> categories = categoryService.getCategories();

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setStatusCode(200);
        baseResponse.setData(categories);
        baseResponse.setMessage("Success");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
