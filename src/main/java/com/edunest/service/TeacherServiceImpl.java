package com.edunest.service;

import com.edunest.common.PasswordUtil;
import com.edunest.configuration.JwtHelper;
import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;
import com.edunest.entity.Teacher;
import com.edunest.error.CustomErrorHolder;
import com.edunest.error.CustomException;
import com.edunest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    PasswordUtil passwordUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Teacher teacher = teacherRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomException(CustomErrorHolder.INVALID_CREDENTIALS));

        if (!teacher.getIsActive()) {
            throw new CustomException(CustomErrorHolder.ACCOUNT_INACTIVE);
        }

        teacher.setLastLogin(LocalDateTime.now());
        teacherRepository.save(teacher);

        String token = jwtHelper.generateAccessToken(teacher);

        return LoginResponse.builder()
                .teacherId(teacher.getTeacherId())
                .tenantId(teacher.getTenantId())
                .roleId(teacher.getRoleId())
                .username(teacher.getUsername())
                .employeeCode(teacher.getEmployeeCode())
                .email(teacher.getEmail())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .token(token)
                .build();
    }
}
