package com.dream.uniclub.controller;

import com.dream.uniclub.request.AuthRequest;
import com.dream.uniclub.response.BaseResponse;
import com.dream.uniclub.service.AuthService;
import com.dream.uniclub.utils.JwtHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/authen")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<?> authen(@Valid @RequestBody AuthRequest authRequest) throws JsonProcessingException {

        // Tạo key RSA
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        // Biến key thành chuỗi để lưu trữ lại
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());

//        boolean isSuccess = authService.checkLogin(authRequest);

//        String token = isSuccess ? jwtHelper.generateToken("Hello Token") : "";

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.email(), authRequest.password()
        );
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        List<GrantedAuthority> listRoles = (List<GrantedAuthority>) authentication.getAuthorities();

        String data = objectMapper.writeValueAsString(listRoles);
        System.out.println("Kiem tra data: " + data);
        String token = jwtHelper.generateToken(data);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(token);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
