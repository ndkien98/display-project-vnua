package com.fita.project.services;

import com.fita.project.dto.YearSemesterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YearSemesterService {
    // Lấy ra tất cả các năm học - học kỳ
    List<YearSemesterDTO> getYearsSemesters();

    // Lấy ra năm học - học kỳ theo "id"
    YearSemesterDTO getYearSemesterById(int id);

    // Thêm năm học - học kỳ
    void addYearSemester(YearSemesterDTO yearSemesterDTO);

    // Sửa năm học - học kỳ
    void editYearSemester(int id, YearSemesterDTO yearSemesterDTO);

    // Xóa năm học - học kỳ
    void deleteYearSemester(int id);
}
