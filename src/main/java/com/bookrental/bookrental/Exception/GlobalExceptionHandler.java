package com.bookrental.bookrental.Exception;

import com.bookrental.bookrental.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse a = new ApiResponse(e.getMessage(), false);
        return new ResponseEntity<ApiResponse>(a, HttpStatus.NOT_FOUND);
    }
}
