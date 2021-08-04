package com.fita.project.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LecturerDTO extends UserDTO {
    private String departmentCode;
    private String departmentName;
}
