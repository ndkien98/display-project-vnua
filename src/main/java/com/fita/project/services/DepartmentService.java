package com.fita.project.services;

import com.fita.project.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    // Lấy ra tất cả các bộ môn
    List<DepartmentDTO> getDepartments();

    // Lấy ra bộ môn theo "id"
    DepartmentDTO getDepartmentById(int id);

    // Lấy ra bộ môn theo "mã bộ môn"
    DepartmentDTO getDepartmentByDepartmentCode(String departmentCode);

    // Thêm bộ môn
    void addDepartment(DepartmentDTO departmentDTO);

    // Sửa bộ môn
    void editDepartment(int id, DepartmentDTO departmentDTO);

    // Xoá bộ môn
    void deleteDepartment(int id);
}
