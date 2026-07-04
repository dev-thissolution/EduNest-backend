package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.configuration.JwtHelper;
import com.edunest.dto.TeacherListResponse;
import com.edunest.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    JwtHelper jwtHelper;

    @GetMapping("/List")
    public ResponseEntity<ResponseObject<List<TeacherListResponse>>> getTeacherList(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        int tenantId = jwtHelper.extractTenantId(token);

        List<TeacherListResponse> teacherList = teacherService.getTeacherList(tenantId);

        ResponseObject<List<TeacherListResponse>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(teacherList);

        return ResponseEntity.ok(response);
    }

}
