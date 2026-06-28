package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;
import com.edunest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        ResponseObject<LoginResponse> responseObject = new ResponseObject<>();
        LoginResponse loginResponse = authService.login(loginRequest);
        responseObject.setData(loginResponse);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
