package com.edunest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_subject", schema = "school")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_subject_id")
    private Long classSubjectId;

    @Column(name = "tenant_id", nullable = false)
    private Integer tenantId;

    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "academic_year_id", nullable = false)
    private Long academicYearId;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}