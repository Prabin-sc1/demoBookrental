package com.bookrental.bookrental.exception;

import com.bookrental.bookrental.enums.ResponseStatus;
import com.bookrental.bookrental.generic.GlobalApiResponse;
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
    private static final ResponseStatus API_FAIL_STATUS = ResponseStatus.FAIL;

    @ExceptionHandler({AppException.class})
    public ResponseEntity<GlobalApiResponse> handleBusinessExceptionAgain(AppException b) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setResponseStatus(API_FAIL_STATUS);
        globalApiResponse.setMessage(b.getMessage());
        globalApiResponse.setData(null);
        return new ResponseEntity<>(globalApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            resp.put(fieldName, message);
        });

        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setResponseStatus(API_FAIL_STATUS);
        globalApiResponse.setMessage(resp.toString());
        globalApiResponse.setData(null);
        return new ResponseEntity<>(globalApiResponse, HttpStatus.BAD_REQUEST);
    }

}
