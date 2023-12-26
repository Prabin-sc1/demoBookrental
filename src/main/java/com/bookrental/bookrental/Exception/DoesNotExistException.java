package com.bookrental.bookrental.Exception;

public class DoesNotExistException extends RuntimeException {

    public DoesNotExistException(String key, String column) {
        super("No element exists of key " + key + " for column " + column + ".");
    }
}
