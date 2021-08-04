package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Integer id;
    private String projectCode;
    private String projectName;
    private String projectAvatarUrl;
    private String shortDescription;
    private String detailedDescription;
    private String demoLink;
    private String categoryCode;
    private String categoryName;
    private String studentCode;
    private String studentName;
    private String studentClass;
    private Integer courseId;
    private String subjectCode;
    private String subjectName;
    private Integer subjectGroup;
    private String courseClass;
    private Integer yearSemesterId;
    private Integer year;
    private Integer semester;
    private String lecturerCode;
    private String lecturerName;
    private Integer status;
    private String createdDate;
    private String createdBy;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private String nameDepartment;
    private String yearSemester;
    private List<ProjectMemberDTO> projectMembers;
}
