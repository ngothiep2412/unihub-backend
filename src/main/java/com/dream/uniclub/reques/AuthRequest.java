package com.dream.uniclub.reques;

//@Data
//public class AuthRequest {
//    private String email;
//    private String password;
//}

// md5, sha-1, bcrypt, pgp
// md5, sha-1 mã hoá ra cùng 1 chuỗi
// pgp -> sinh ra 1 cặp key (1 key chuyên giải mã, 1 key chuyên mã hoá- public key -> phải có private key)

public record AuthRequest(String email, String password) {

}