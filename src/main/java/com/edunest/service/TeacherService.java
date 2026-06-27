package com.edunest.service;

import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;

public interface TeacherService {
    LoginResponse login(LoginRequest loginRequest);
}
