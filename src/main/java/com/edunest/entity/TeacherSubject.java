package com.edunest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher_subject", schema = "school")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_subject_id")
    private Long teacherSubjectId;

    @Column(name = "tenant_id", nullable = false)
    private Integer tenantId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}