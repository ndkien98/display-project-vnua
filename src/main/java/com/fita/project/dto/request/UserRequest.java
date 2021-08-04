package com.fita.project.dto.request;

import lombok.Data;

@Data
public class UserRequest {

    private String userName;
    private String password;
    private String fullName;
    private String birthDay;
    private int status;
    private int roleId;
}
