package com.sumit.user.service.exceptions;

import com.sumit.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // make centralize exception of project
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // when in project anywhere ResourceNotFoundException.class is thrown this method will be called
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message= ex.getMessage();
        ApiResponse res = ApiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
    }
}
