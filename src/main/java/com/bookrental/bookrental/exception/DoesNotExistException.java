package com.bookrental.bookrental.exception;

public class DoesNotExistException extends RuntimeException {

    public DoesNotExistException(String key, String column) {
        super("No element exists of key " + key + " for column " + column + ".");
    }
}
