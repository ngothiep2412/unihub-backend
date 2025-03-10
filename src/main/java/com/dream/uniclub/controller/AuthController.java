package com.dream.uniclub.controller;


import com.dream.uniclub.reques.AuthRequest;
import com.dream.uniclub.response.BaseResponse;
import com.dream.uniclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authen")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping
    public ResponseEntity<?> authen(@RequestBody AuthRequest authRequest) {
        boolean isSuccess = authService.checkLogin(authRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(isSuccess);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
