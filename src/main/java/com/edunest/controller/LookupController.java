package com.edunest.controller;

import com.edunest.common.ResponseObject;
import com.edunest.entity.Role;
import com.edunest.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/role")
    public ResponseEntity<ResponseObject<List<Role>>> getAllRoles() {
        ResponseObject<List<Role>> responseObject = new ResponseObject<>();
        List<Role> roles = lookupService.getAllRoles();
        responseObject.setData(roles);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}
