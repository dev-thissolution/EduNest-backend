package com.edunest.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {
    private String       className;
    private Integer      academicYearId;
    private List<Integer> subjectIds;
}