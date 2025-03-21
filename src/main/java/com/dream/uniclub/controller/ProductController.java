package com.dream.uniclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.uniclub.request.AddProductRequest;
import com.dream.uniclub.response.BaseResponse;
import com.dream.uniclub.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(AddProductRequest addProductRequest) {
        productService.addProduct(addProductRequest);

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setStatusCode(200);

        baseResponse.setMessage("Add product success");

        return new ResponseEntity<>("Hello add product", HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity<?> getProduct(@PathVariable int page) {
        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Success !");
        baseResponse.setData(productService.getProduct(page));

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Success !");
        baseResponse.setData(productService.getProductById(id));

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
