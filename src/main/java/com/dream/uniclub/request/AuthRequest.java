package com.dream.uniclub.request;

//@Data
//public class AuthRequest {
//    private String email;
//    private String password;
//}

// md5, sha-1, bcrypt, pgp
// md5, sha-1 mã hoá ra cùng 1 chuỗi
// pgp -> sinh ra 1 cặp key (1 key chuyên giải mã, 1 key chuyên mã hoá- public key -> phải có private key)

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotNull(message = "Email cannot be null")
        @NotEmpty(message = "Email cannot be empty")
        @Email(message = "Email is not valid")
        String email,

        @NotEmpty(message = "Password cannot be empty")
        @NotNull(message = "Password cannot be null")
        @Size(message = "Password must be at least 6 characters", min = 6)
        String password
) {

}