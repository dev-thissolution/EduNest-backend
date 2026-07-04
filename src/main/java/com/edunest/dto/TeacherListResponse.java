package com.edunest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherListResponse {
    private int teacherId;
    private int roleId;
    private int employmentId;
    private String mobileNo;
    private String email;
    private String teacherName;
    private LocalDateTime lastLogin;
    private LocalDateTime updatedDate;
    private String updatedBy;
}