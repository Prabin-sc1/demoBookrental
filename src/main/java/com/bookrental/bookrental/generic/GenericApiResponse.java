package com.bookrental.bookrental.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
    @AllArgsConstructor
public class GenericApiResponse<T> {
    private Boolean status;
    private String message;
    private T data;

    public static <T> GenericApiResponse<T> empty() {
        return GenericApiResponse.<T>builder().
                status(false).
                message("Error").
                data(null)
                .build();
    }

    public static <T> GenericApiResponse<T> success(T data) {
        return GenericApiResponse.<T>builder().
                status(true).
                message("Success").
                data(data)
                .build();
    }


    public static <T> GenericApiResponse<T> error(T data) {
        return GenericApiResponse.<T>builder().
                status(false).
                message("Error")
                .build();
    }


    public static <T> GenericApiResponse<T> success(T data, String message) {
        return GenericApiResponse.<T>builder().
                status(true).
                message(message).
                data(data)
                .build();
    }


    public static <T> GenericApiResponse<T> error(T data, String message) {
        return GenericApiResponse.<T>builder().
                status(false).
                message(message)
                .build();
    }
}
