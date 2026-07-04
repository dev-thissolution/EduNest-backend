package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.configuration.JwtHelper;
import com.edunest.entity.ClassMaster;
import com.edunest.entity.EmploymentType;
import com.edunest.entity.Role;
import com.edunest.entity.Subject;
import com.edunest.service.LookupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lookup")
public class LookupController {

    @Autowired
    LookupService lookupService;

    @Autowired
    JwtHelper jwtHelper;

    @GetMapping("/role")
    public ResponseEntity<ResponseObject<List<Role>>> getAllRoles() {
        List<Role> roles = lookupService.getAllRoles();

        ResponseObject<List<Role>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setErrors(null);
        response.setData(roles);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/employmentTypes")
    public ResponseEntity<ResponseObject<List<EmploymentType>>> getAllEmployType() {
        List<EmploymentType> employmentTypes = lookupService.getAllEmployType();

        ResponseObject<List<EmploymentType>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(employmentTypes);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject")
    public ResponseEntity<ResponseObject<List<Subject>>> getAllSubject(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        int tenantId = jwtHelper.extractTenantId(token);

        List<Subject> subjects = lookupService.getAllSubject(tenantId);

        ResponseObject<List<Subject>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(subjects);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/classMaster")
    public ResponseEntity<ResponseObject<List<ClassMaster>>> getAllClassMaster(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        int tenantId = jwtHelper.extractTenantId(token);

        List<ClassMaster> classMasters = lookupService.getAllClassMaster(tenantId);

        ResponseObject<List<ClassMaster>> response = new ResponseObject<>();
        response.setSuccess(true);
        response.setData(classMasters);

        return ResponseEntity.ok(response);
    }
}