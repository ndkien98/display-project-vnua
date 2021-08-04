package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "project_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_code", length = 10)
    private String studentCode;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "class_code", length = 10)
    private String classCode;

    @Column(name = "project_code", length = 10)
    private String projectCode;
}
