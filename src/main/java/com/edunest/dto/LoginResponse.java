package com.edunest.dto;

import com.edunest.entity.Teacher;
import com.edunest.entity.Tenant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String session;
    private String refresh;
    private Teacher teacher;
    private Tenant tenant;
}