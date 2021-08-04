package com.fita.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearSemesterDTO {
    private Integer id;
    private Integer year;
    private Integer semester;
    private String startDate;
    private Integer weeksNumber;
}
