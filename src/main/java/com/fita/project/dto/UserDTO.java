package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String birthDate;
    private String gender;
    private String emailAddress;
    private String phoneNumber;
    private Integer roleId;
    private String roleName;
    private String userAvatarUrl;
    private Integer status;
    private String createdDate;
    private String createdBy;
    private String token;
}
