package com.example.blogapp.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class GenericResponse {

    private String message;
    private String status;
    private HttpStatus httpStatus;
    private Object data;

    public GenericResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public GenericResponse(String message, String status, HttpStatus httpStatus) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public GenericResponse(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public GenericResponse(String message, String status, Object data,HttpStatus httpStatus) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.httpStatus = httpStatus;
    }
}
