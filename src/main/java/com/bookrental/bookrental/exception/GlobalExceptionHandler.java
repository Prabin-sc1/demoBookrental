package com.bookrental.bookrental.exception;

import com.bookrental.bookrental.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        ApiResponse a = new ApiResponse(e.getMessage(), false);
        return new ResponseEntity<>(a, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookStockException.class, MemberOutstandingRentalsException.class})
    public Map<String, String> handleBusinessException(Exception b) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", b.getMessage());
        return errorMap;
    }
}
