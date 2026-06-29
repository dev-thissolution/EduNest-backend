package com.edunest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long teacherId;
    private Integer tenantId;
    private Integer roleId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
}