package com.edunest.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject<T> {
    private T data;
    private Boolean success = true;
    private String title;
    private String message;

}
