package com.fita.project.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "years_semesters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearSemester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "weeks_number")
    private Integer weeksNumber;
}
