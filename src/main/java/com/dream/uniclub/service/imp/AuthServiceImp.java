package com.dream.uniclub.service.imp;

import com.dream.uniclub.entity.UserEntity;
import com.dream.uniclub.repository.UserRepository;
import com.dream.uniclub.reques.AuthRequest;
import com.dream.uniclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(AuthRequest authRequest) {
        boolean isSuccess = false;
        UserEntity user = userRepository.findUserEntityByEmail(authRequest.email());

        if (user != null && passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            isSuccess = true;
        }

        return isSuccess;
    }
}
