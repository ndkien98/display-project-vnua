package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles_functions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "function_id", nullable = false)
    private Integer functionId;

    @Column(name = "status", nullable = false)
    private Integer status;
}
