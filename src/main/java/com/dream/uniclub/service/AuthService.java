package com.dream.uniclub.service;

import com.dream.uniclub.dto.RoleDTO;
import com.dream.uniclub.request.AuthRequest;

import java.util.List;

/**
 * {
 *     statusCode: 200,
 *     message: "",
 *     data: true
 * }
 */

public interface AuthService {
    List<RoleDTO> checkLogin(AuthRequest authRequest);
}
