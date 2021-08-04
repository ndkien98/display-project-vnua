package com.fita.project.dto.reponses;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Response {
    @Expose
    private int errorCode;
    @Expose
    private String message;
    @Expose
    private Object data;
    public Response() {
    }
}
