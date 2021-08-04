package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;
    private String subjectCode;
    private String subjectName;
    private Integer subjectGroup;
    private String classCode;
    private Integer yearSemesterId;
    private Integer year;
    private Integer semester;
    private String lecturerCode;
    private String lecturerName;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
}
