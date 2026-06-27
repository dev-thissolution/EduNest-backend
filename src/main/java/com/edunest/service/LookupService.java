package com.edunest.service;

import com.edunest.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LookupService {

    List<Role> getAllRoles();
}
