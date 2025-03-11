package com.dream.uniclub.filter;

import com.dream.uniclub.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorHeader = request.getHeader("Authorization");

        if (authorHeader != null && authorHeader.startsWith("Bearer ")) {
            String token = authorHeader.substring(7);
            String data = jwtHelper.decodeToken(token);

            if (data != null) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());

                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
