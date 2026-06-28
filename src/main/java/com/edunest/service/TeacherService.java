package com.edunest.service;

import com.edunest.dto.TeacherListResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherListResponse> getTeacherList(Integer tenantId);
}
