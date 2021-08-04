package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 10, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "gender", length = 5)
    private String gender;

    @Column(name = "email_address", length = 100, unique = true)
    private String emailAddress;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "user_avatar_url")
    private String userAvatarUrl;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "created_by", length = 10)
    private String createdBy;
}
