package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunctionDTO {
    private Integer id;
    private Integer roleId;
    private String roleName;
    private Integer functionId;
    private String functionName;
    private String actionCode;
}
