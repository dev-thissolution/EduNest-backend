package com.edunest.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomError {
    private String error;
    private String errorTitle;
    private String errorMessage;

    public CustomError(String error, String errorTitle, String errorMessage) {
        this.error = error;
        this.errorTitle = errorTitle;
        this.errorMessage = errorMessage;
    }

}
