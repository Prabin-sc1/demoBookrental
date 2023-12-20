package com.bookrental.bookrental.pojo;

import com.bookrental.bookrental.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalApiResponse implements Serializable {
    private ResponseStatus status;
    private String message;
    private Object data;

    public void setResponse(ResponseStatus status, String message, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
