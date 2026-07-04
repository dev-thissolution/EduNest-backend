package com.edunest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherClassRequest {
    private Integer classId;
    private String  section;
    private Integer academicYearId;
    private Boolean isClassTeacher;
}