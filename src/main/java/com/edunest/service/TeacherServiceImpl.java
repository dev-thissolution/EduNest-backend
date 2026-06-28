package com.edunest.service;

import com.edunest.dto.TeacherListResponse;
import com.edunest.entity.EmploymentType;
import com.edunest.entity.Role;
import com.edunest.entity.Teacher;
import com.edunest.repository.EmploymentTypeRepository;
import com.edunest.repository.RoleRepository;
import com.edunest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmploymentTypeRepository employmentTypeRepository;

    @Override
    public List<TeacherListResponse> getTeacherList(Integer tenantId) {
        List<Teacher> teachers = teacherRepository.findByTenantIdAndIsActiveTrue(tenantId);
        List<TeacherListResponse> responseList = new ArrayList<>();

        for (Teacher teacher : teachers) {
            Role role = roleRepository.findById(teacher.getRoleId()).orElse(null);
            EmploymentType employmentType = employmentTypeRepository.findById(teacher.getEmploymentTypeId()).orElse(null);

            String updatedByName = null;
            Teacher updatedByTeacher = teacherRepository.findById(teacher.getUpdatedBy()).orElse(null);
            if (updatedByTeacher != null) {
                updatedByName = updatedByTeacher.getFirstName() + " " + updatedByTeacher.getLastName();
            }
            TeacherListResponse response = new TeacherListResponse();
            response.setTeacherId(teacher.getTeacherId());
            response.setMobileNo(teacher.getMobileNo());
            response.setEmail(teacher.getEmail());
            response.setTeacherName(teacher.getFirstName() + " " + teacher.getLastName());
            response.setLastLogin(teacher.getLastLogin());
            response.setRoleName(role != null ? role.getRoleName() : null);
            response.setEmploymentType(employmentType != null ? employmentType.getEmploymentType() : null);
            response.setUpdatedDate(teacher.getUpdatedDate());
            response.setUpdatedBy(updatedByName);

            responseList.add(response);
        }
        return responseList;
    }
}
