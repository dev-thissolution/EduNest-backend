package com.edunest.error;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomException extends RuntimeException {
    private String error;
    private String errorTitle;
    private String errorMessage;

    public CustomException(CustomError error) {
        super(error.getError());
        this.error = error.getError();
        this.errorTitle = error.getErrorTitle();
        this.errorMessage = error.getErrorMessage();
    }

}
