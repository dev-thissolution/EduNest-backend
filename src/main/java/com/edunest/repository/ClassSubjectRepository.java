package com.edunest.repository;

import com.edunest.entity.ClassSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSubjectRepository extends JpaRepository<ClassSubject, Integer> {
    List<ClassSubject> findByClassIdAndTenantId(Integer classId, Integer tenantId);
}