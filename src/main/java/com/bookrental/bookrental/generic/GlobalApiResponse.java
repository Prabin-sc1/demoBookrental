package com.bookrental.bookrental.generic;

import com.bookrental.bookrental.enums.ResponseStatus;
import lombok.*;

@Getter
@Setter
public class GlobalApiResponse {
    private ResponseStatus responseStatus;
    private String message;
    private Object data;
}


