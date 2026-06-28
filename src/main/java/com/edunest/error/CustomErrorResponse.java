package com.edunest.error;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CustomErrorResponse {
    private String error;
    private String errorTitle;
    private String errorMessage;
    private String path;
    private Integer status;
    private LocalDateTime timestamp;

}
