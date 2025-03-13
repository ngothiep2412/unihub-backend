package com.dream.uniclub.security;

import com.dream.uniclub.dto.RoleDTO;
import com.dream.uniclub.exception.AuthenException;
import com.dream.uniclub.request.AuthRequest;
import com.dream.uniclub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthRequest authRequest = new AuthRequest(username, password);
        List<RoleDTO> roleDTOs = authService.checkLogin(authRequest);

        if (!roleDTOs.isEmpty()) {
            // Stream API
            // map(): Cho phép biến đổi kiểu dữ liệu gốc thành kiểu dữ liệu khác trong quá trình duyệt mảng/đối tg
            List<SimpleGrantedAuthority> authorities = roleDTOs.stream().map(item ->
                    new SimpleGrantedAuthority(item.getName())).toList();

//            List<GrantedAuthority> authorities = new ArrayList<>();  // chỉnh lai Stream API
//
//            roleDTOs.forEach(roleDTO -> {
//                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleDTO.getName());
//                authorities.add(simpleGrantedAuthority);
//            });

            return new UsernamePasswordAuthenticationToken("", "", authorities);
        } else {
            throw new AuthenException("Login failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // được sử dụng để kiểm tra xem authentication có phải là UsernamePasswordAuthenticationToken hay không.
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
