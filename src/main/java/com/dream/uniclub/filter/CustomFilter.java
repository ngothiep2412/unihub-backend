package com.dream.uniclub.filter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dream.uniclub.dto.AuthorityDTO;
import com.dream.uniclub.utils.JwtHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorHeader = request.getHeader("Authorization");

        if (authorHeader != null && authorHeader.startsWith("Bearer ")) {
            String token = authorHeader.substring(7);
            String data = jwtHelper.decodeToken(token);

            if (data != null) {
                List<AuthorityDTO> authorityDTOS = objectMapper.readValue(data,
                        new TypeReference<List<AuthorityDTO>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        });

                // List<GrantedAuthority> authorities = new ArrayList<>();
                // authorityDTOS.forEach(authorityDTO -> {
                // SimpleGrantedAuthority simpleGrantedAuthority = new
                // SimpleGrantedAuthority(authorityDTO.getAuthority());
                // authorities.add(simpleGrantedAuthority);
                // });

                List<SimpleGrantedAuthority> authorities = authorityDTOS.stream()
                        .map(item -> new SimpleGrantedAuthority(item.getAuthority())).toList();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("",
                        "", authorities);

                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
