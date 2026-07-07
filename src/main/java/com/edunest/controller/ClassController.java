package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.configuration.JwtHelper;
import com.edunest.dto.ClassListResponse;
import com.edunest.dto.ClassRequest;
import com.edunest.service.ClassService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @Autowired
    JwtHelper jwtHelper;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject<List<ClassListResponse>>> getClassList(
            HttpServletRequest request) {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        Integer tenantId = jwtHelper.extractTenantId(token);

        ResponseObject<List<ClassListResponse>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(classService.getClassList(tenantId));
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{classId}")
    public ResponseEntity<ResponseObject<Boolean>> saveClass(
            HttpServletRequest request,
            @PathVariable(required = false) Integer classId,
            @RequestBody ClassRequest classRequest) {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        Integer tenantId = jwtHelper.extractTenantId(token);
        Integer loginTeacherId = jwtHelper.extractTeacherId(token);

        ResponseObject<Boolean> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(classService.saveClass(classId, tenantId, loginTeacherId, classRequest));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<ResponseObject<String>> deleteClass(
            HttpServletRequest request,
            @PathVariable Integer classId) {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        Integer loginTeacherId = jwtHelper.extractTeacherId(token);

        boolean isDeleted = classService.deleteClass(classId, loginTeacherId);

        ResponseObject<String> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(isDeleted ? "Class deleted successfully" : "Class not deleted");
        return ResponseEntity.ok(response);
    }
}