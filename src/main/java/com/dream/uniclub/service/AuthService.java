package com.dream.uniclub.service;

import com.dream.uniclub.reques.AuthRequest;

/**
 * {
 *     statusCode: 200,
 *     message: "",
 *     data: true
 * }
 */

public interface AuthService {
    boolean checkLogin(AuthRequest authRequest);
}
