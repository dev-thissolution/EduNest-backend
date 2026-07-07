package com.edunest.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassListResponse {
    private Integer      classId;
    private String       className;
    private Boolean      isActive;
    private List<String> subjects;
}