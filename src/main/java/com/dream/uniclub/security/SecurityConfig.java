package com.dream.uniclub.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dream.uniclub.filter.CustomFilter;

@Configuration // quét config trc khi start
@EnableWebSecurity
public class SecurityConfig {

    // Tính đa hình
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Allow all origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow specific
                                                                                                   // methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Allow specific
                                                                                                   // headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationProvider(HttpSecurity http,
            CustomAuthenticationProvider customAuthenProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomFilter customFilter,
            @Qualifier("corsConfigurationSource") CorsConfigurationSource corsSource) throws Exception {
        return http
                .csrf((AbstractHttpConfigurer::disable)) // (csfr -> csfr.disable())
                .cors(cors -> cors.configurationSource(corsSource))
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // chặn session
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/authen", "/file/**").permitAll();
                    // request.requestMatchers(HttpMethod.GET, "/product").has
                    request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                    request.requestMatchers("/product").hasAuthority("USER"); // hasRole() -> thì phải lưu role là //
                                                                              // ROLE_ADMIN
                    request.requestMatchers("/category", "/brand").permitAll();

                    request.anyRequest().authenticated();
                })
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
