package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;
import com.edunest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        ResponseObject<LoginResponse> responseObject = new ResponseObject<>();
        LoginResponse loginResponse = teacherService.login(loginRequest);
        responseObject.setData(loginResponse);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
