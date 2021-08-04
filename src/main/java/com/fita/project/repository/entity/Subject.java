package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_code", length = 10, nullable = false, unique = true)
    private String subjectCode;

    @Column(name = "subject_name", length = 200, nullable = false)
    private String subjectName;

    @Column(name = "department_code", length = 5, nullable = false)
    private String departmentCode;
}
