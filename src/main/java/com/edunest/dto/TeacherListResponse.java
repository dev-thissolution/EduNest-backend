package com.edunest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherListResponse {
    private Long teacherId;
    private String mobileNo;
    private String email;
    private String teacherName;
    private LocalDateTime lastLogin;
    private String roleName;
    private String employmentType;
    private LocalDateTime updatedDate;
    private String updatedBy;
}