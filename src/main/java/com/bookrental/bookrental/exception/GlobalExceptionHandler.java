package com.bookrental.bookrental.exception;

import com.bookrental.bookrental.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler({BookStockException.class, MemberOverdewRentalException.class, InvalidTransactionStateException.class,
            BookAlreadyExistsException.class,
            CategoryAlreadyExistsException.class})
    public Map<String, String> handleBusinessException(Exception b) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", b.getMessage());
        return errorMap;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
