package com.dream.uniclub.service.imp;

import com.dream.uniclub.dto.RoleDTO;
import com.dream.uniclub.entity.RoleEntity;
import com.dream.uniclub.entity.UserEntity;
import com.dream.uniclub.repository.UserRepository;
import com.dream.uniclub.request.AuthRequest;
import com.dream.uniclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<RoleDTO> checkLogin(AuthRequest authRequest) {
        List<RoleDTO> roles = new ArrayList<>();

        UserEntity user = userRepository.findUserEntityByEmail(authRequest.email());
        if (user != null && passwordEncoder.matches(authRequest.password(), user.getPassword())) {
            RoleEntity userRole = user.getRole();

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(userRole.getId());
            roleDTO.setName(userRole.getName()); // chờ bữa sau dùng Stream API
            roles.add(roleDTO);
        }

        return roles;
    }
}
