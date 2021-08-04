package com.fita.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseResponse {

    private int errorCode;
    private Object message;
    private Object data;

    public BaseResponse() {
        this.errorCode = 404;
        this.message = "Error";
    }

}
