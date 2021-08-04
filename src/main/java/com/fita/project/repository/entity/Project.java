package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_code", length = 10, nullable = false, unique = true)
    private String projectCode;

    @Column(name = "project_name", length = 200, nullable = false)
    private String projectName;

    @Column(name = "project_avatar_url", length = 500)
    private String projectAvatarUrl;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "detailed_description")
    private String detailedDescription;

    @Column(name = "demo_link", length = 500)
    private String demoLink;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "student_code", length = 10)
    private String studentCode;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by", length = 10)
    private String createdBy;

    @Column(name = "last_modified_by", length = 10)
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private String lastModifiedDate;
}
