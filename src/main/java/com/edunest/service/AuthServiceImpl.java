package com.edunest.service;

import com.edunest.configuration.JwtHelper;
import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;
import com.edunest.entity.Teacher;
import com.edunest.entity.Tenant;
import com.edunest.error.CustomException;
import com.edunest.helper.CryptoHelper;
import com.edunest.repository.TeacherRepository;
import com.edunest.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    JwtHelper jwtHelper;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Teacher teacher = teacherRepository.findByEmail(loginRequest.getEmail()).
                orElseThrow(() -> new CustomException("Teacher", "Teacher not found"));

        if (!teacher.getIsActive()) {
            throw new CustomException("Teacher", "Account is inactive. Please contact admin");
        }

        Tenant tenant = tenantRepository.findById(teacher.getTenantId()).orElseThrow(() -> new CustomException("Teacher", "Tenant not found"));

        String encryptedPassword = CryptoHelper.encryptPassword(loginRequest.getPassword(), teacher.getHashkey());
        if (!encryptedPassword.equals(teacher.getPassword())) {
            throw new CustomException("Teacher", "Invalid email or password");
        }

        teacher.setLastLogin(LocalDateTime.now());
        teacherRepository.save(teacher);

        String session = jwtHelper.generateAccessToken(teacher);
        String refresh = jwtHelper.generateAccessToken(teacher);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSession(session);
        loginResponse.setRefresh(refresh);
        loginResponse.setTeacher(teacher);
        loginResponse.setTenant(tenant);

        return loginResponse;
    }
}