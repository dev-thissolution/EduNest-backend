package com.edunest.service;

import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
