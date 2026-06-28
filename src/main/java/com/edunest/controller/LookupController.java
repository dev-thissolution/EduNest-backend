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
import org.springframework.http.HttpStatus;
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
        ResponseObject<List<Role>> responseObject = new ResponseObject<>();
        List<Role> roles = lookupService.getAllRoles();
        responseObject.setData(roles);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/employmentTypes")
    public ResponseEntity<ResponseObject<List<EmploymentType>>> getAllEmployType() {
        ResponseObject<List<EmploymentType>> responseObject = new ResponseObject<>();
        List<EmploymentType> employmentTypes = lookupService.getAllEmployType();
        responseObject.setData(employmentTypes);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<ResponseObject<List<Subject>>> getAllSubject(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        int tenantId = jwtHelper.extractTenantId(token);
        ResponseObject<List<Subject>> responseObject = new ResponseObject<>();
        List<Subject> subjects = lookupService.getAllSubject(tenantId);
        responseObject.setData(subjects);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/classMaster")
    public ResponseEntity<ResponseObject<List<ClassMaster>>> getAllClassMaster(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = jwtHelper.cleanToken(authHeader);
        int tenantId = jwtHelper.extractTenantId(token);
        ResponseObject<List<ClassMaster>> responseObject = new ResponseObject<>();
        List<ClassMaster> classMasters = lookupService.getAllClassMaster(tenantId);
        responseObject.setData(classMasters);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
