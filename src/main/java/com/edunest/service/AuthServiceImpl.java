package com.edunest.service;

import com.edunest.configuration.JwtHelper;
import com.edunest.dto.LoginRequest;
import com.edunest.dto.LoginResponse;
import com.edunest.entity.Teacher;
import com.edunest.error.CustomErrorHolder;
import com.edunest.error.CustomException;
import com.edunest.helper.CryptoHelper;
import com.edunest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    JwtHelper jwtHelper;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Teacher teacher = teacherRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new CustomException(CustomErrorHolder.INVALID_CREDENTIALS));

        if (!teacher.getIsActive()) {
            throw new CustomException(CustomErrorHolder.ACCOUNT_INACTIVE);
        }

        String encryptedPassword = CryptoHelper.encryptPassword(loginRequest.getPassword(), teacher.getHashkey());
        if (!encryptedPassword.equals(teacher.getPassword())) {
            throw new CustomException(CustomErrorHolder.INVALID_CREDENTIALS);
        }

        teacher.setLastLogin(LocalDateTime.now());
        teacherRepository.save(teacher);

        String token = jwtHelper.generateAccessToken(teacher);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setTeacherId(teacher.getTeacherId());
        loginResponse.setTenantId(teacher.getTenantId());
        loginResponse.setRoleId(teacher.getRoleId());
        loginResponse.setUsername(teacher.getUsername());
        loginResponse.setEmail(teacher.getEmail());
        loginResponse.setFirstName(teacher.getFirstName());
        loginResponse.setLastName(teacher.getLastName());
        loginResponse.setToken(token);

        return loginResponse;
    }
}
