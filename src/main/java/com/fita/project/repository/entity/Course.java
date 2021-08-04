package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_code", length = 10, nullable = false)
    private String subjectCode;

    @Column(name = "subject_group", nullable = false)
    private Integer subjectGroup;

    @Column(name = "class_code", length = 50)
    private String classCode;

    @Column(name = "year_semester_id")
    private Integer yearSemesterId;

    @Column(name = "lecturer_code", length = 10)
    private String lecturerCode;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by", length = 10)
    private String createdBy;

    @Column(name = "last_modified_date")
    private String lastModifiedDate;
}
