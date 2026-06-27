package com.edunest.service;

import com.edunest.entity.Role;
import com.edunest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupServiceImpl implements LookupService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
