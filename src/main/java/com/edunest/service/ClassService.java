package com.edunest.service;

import com.edunest.dto.ClassListResponse;
import com.edunest.dto.ClassRequest;

import java.util.List;

public interface ClassService {
    List<ClassListResponse> getClassList(Integer tenantId);

    boolean saveClass(Integer classId, Integer tenantId, Integer loginTeacherId, ClassRequest request);

    boolean deleteClass(Integer classId, Integer loginTeacherId);
}