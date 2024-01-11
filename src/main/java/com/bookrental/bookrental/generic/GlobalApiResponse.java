package com.bookrental.bookrental.generic;

import com.bookrental.bookrental.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GlobalApiResponse {
    private ResponseStatus responseStatus;
    private String message;
    private Object data;
}
