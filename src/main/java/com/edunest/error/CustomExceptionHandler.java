package com.edunest.error;

import com.edunest.common.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseObject<Object>> handleCustomException(CustomException ex) {

        ResponseObject<Object> response = new ResponseObject<>();
        response.setSuccess(false);
        response.setData(null);
        response.setErrors(Collections.singletonList(new ErrorItem(ex.getParam(), ex.getMsg())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}