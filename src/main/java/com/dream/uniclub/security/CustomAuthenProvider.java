package com.dream.uniclub.security;

import com.dream.uniclub.request.AuthRequest;
import com.dream.uniclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthRequest authRequest = new AuthRequest(username, password);

        boolean isSuccess = authService.checkLogin(authRequest);

        if (isSuccess) {
            return new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
