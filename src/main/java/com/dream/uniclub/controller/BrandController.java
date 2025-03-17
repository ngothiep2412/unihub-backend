package com.dream.uniclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.uniclub.dto.BrandDTO;
import com.dream.uniclub.response.BaseResponse;
import com.dream.uniclub.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getBrand() {
        List<BrandDTO> brands = brandService.getAllBrands();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Success");
        baseResponse.setData(brands);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
