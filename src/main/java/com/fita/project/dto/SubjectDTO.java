package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Integer id;
    private String subjectCode;
    private String subjectName;
    private String departmentCode;
    private String departmentName;
}
