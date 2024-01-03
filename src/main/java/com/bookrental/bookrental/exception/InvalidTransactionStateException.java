package com.bookrental.bookrental.exception;

public class InvalidTransactionStateException extends AppException{
    public InvalidTransactionStateException(String message) {
        super(message);
    }
}
