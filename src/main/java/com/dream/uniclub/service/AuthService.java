package com.dream.uniclub.service;

import com.dream.uniclub.request.AuthRequest;

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
