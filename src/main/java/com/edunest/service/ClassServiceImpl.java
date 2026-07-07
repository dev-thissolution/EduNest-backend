package com.edunest.service;

import com.edunest.dto.ClassListResponse;
import com.edunest.dto.ClassRequest;
import com.edunest.entity.ClassMaster;
import com.edunest.entity.ClassSubject;
import com.edunest.entity.Subject;
import com.edunest.error.CustomException;
import com.edunest.repository.ClassMasterRepository;
import com.edunest.repository.ClassSubjectRepository;
import com.edunest.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMasterRepository classMasterRepository;

    @Autowired
    ClassSubjectRepository classSubjectRepository;

    @Autowired
    SubjectRepository subjectRepository;

    // ── Get Class List ─────────────────────────────────────────────────────────
    @Override
    public List<ClassListResponse> getClassList(Integer tenantId) {
        List<ClassMaster> classes = classMasterRepository.findByTenantIdAndIsActiveTrue(tenantId);
        List<ClassListResponse> responseList = new ArrayList<>();

        for (ClassMaster classMaster : classes) {
            List<ClassSubject> classSubjects = classSubjectRepository
                    .findByClassIdAndTenantId(classMaster.getClassId(), tenantId);

            List<String> subjectNames = new ArrayList<>();
            for (ClassSubject cs : classSubjects) {
                Subject subject = subjectRepository.findById(cs.getSubjectId()).orElse(null);
                if (subject != null) {
                    subjectNames.add(subject.getSubjectName());
                }
            }

            ClassListResponse response = new ClassListResponse();
            response.setClassId(classMaster.getClassId());
            response.setClassName(classMaster.getClassName());
            response.setIsActive(classMaster.getIsActive());
            response.setSubjects(subjectNames);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    @Transactional
    public boolean saveClass(Integer classId, Integer tenantId,
                             Integer loginTeacherId, ClassRequest request) {

        boolean isEdit = (classId != null);
        ClassMaster classMaster;

        if (isEdit) {
            classMaster = classMasterRepository.findById(classId)
                    .orElseThrow(() -> new CustomException("Class", "Class not found"));
            classMaster.setClassName(request.getClassName());

        } else {
            if (classMasterRepository.existsByClassNameAndTenantId(request.getClassName(), tenantId)) {
                throw new CustomException("className", "Class already exists");
            }
            classMaster = new ClassMaster();
            classMaster.setTenantId(tenantId);
            classMaster.setClassName(request.getClassName());
            classMaster.setIsActive(true);
        }

        ClassMaster savedClass = classMasterRepository.save(classMaster);
        Integer savedClassId = savedClass.getClassId();

        if (request.getSubjectIds() != null && !request.getSubjectIds().isEmpty()) {
            if (isEdit) {
                List<ClassSubject> oldSubjects = classSubjectRepository
                        .findByClassIdAndTenantId(savedClassId, tenantId);
                classSubjectRepository.deleteAll(oldSubjects);
            }

            for (Integer subjectId : request.getSubjectIds()) {
                ClassSubject classSubject = new ClassSubject();
                classSubject.setTenantId(tenantId);
                classSubject.setClassId(savedClassId);
                classSubject.setSubjectId(subjectId);
                classSubject.setAcademicYearId(request.getAcademicYearId());
                classSubject.setIsActive(true);
                classSubjectRepository.save(classSubject);
            }
        }

        return true;
    }

    @Override
    public boolean deleteClass(Integer classId, Integer loginTeacherId) {
        ClassMaster classMaster = classMasterRepository.findById(classId)
                .orElseThrow(() -> new CustomException("Class", "Class not found"));
        classMaster.setIsActive(false);
        classMasterRepository.save(classMaster);
        return true;
    }
}