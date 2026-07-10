package com.edunest.repository;

import com.edunest.entity.ClassMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMasterRepository extends JpaRepository<ClassMaster, Integer> {
    List<ClassMaster> findByTenantIdAndIsActiveTrue(Integer tenantId);

    boolean existsByClassNameAndTenantId(String className, Integer tenantId);
}
