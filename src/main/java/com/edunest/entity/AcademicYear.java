package com.edunest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academic_year", schema = "lookup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academic_year_id")
    private Integer academicYearId;

    @Column(name = "tenant_id", nullable = false)
    private Integer tenantId;

    @Column(name = "year_name", nullable = false, length = 20)
    private String yearName;

    @Column(name = "start_date", nullable = false)
    private java.time.LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private java.time.LocalDate endDate;

    @Column(name = "is_current")
    private Boolean isCurrent = false;

    @Column(name = "is_active")
    private Boolean isActive = true;
}