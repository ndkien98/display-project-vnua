package com.fita.project.services;

import com.fita.project.dto.ProjectDTO;
import com.fita.project.dto.YearDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProjectService {
    // Lấy ra tất cả các đồ án
    List<ProjectDTO> getProjects();

    // Lấy ra đồ án theo "trạng thái"
    List<ProjectDTO> getProjectsByStatus(int status);

    // Lấy ra đồ án theo "id"
    ProjectDTO getProjectById(int id);

    // Lấy ra đồ án theo "mã đồ án"
    ProjectDTO getProjectByProjectCode(String projectCode);

    // Lấy ra đồ án theo "mã sinh viên"
    List<ProjectDTO> getProjectsByStudentCode(String studentCode);

    // Lấy ra đồ án theo "mã thể loại"
    List<ProjectDTO> getProjectsByCategoryCode(String categoryCode);

    // Lấy ra đồ án theo "mã giảng viên"
    List<ProjectDTO> getProjectsByLecturerCode(String lecturerCode);

    // Lấy ra đồ án theo "lớp học phần id"
    List<ProjectDTO> getProjectsByCourseId(int courseId);

    // Lấy ra đồ án theo "năm học - học kỳ id"
    List<ProjectDTO> getProjectsByYearSemesterId(int yearSemesterId);

    // Lấy ra đồ án theo "mã giảng viên + trạng thái";
    List<ProjectDTO> getProjects(String lecturerCode, int status);

    // Lấy ra đồ án theo "mã giảng viên + trạng thái + năm học - học kỳ id"
    List<ProjectDTO> getProjects(String lecturerCode, int status, int yearSemesterId);

    // Lấy ra số lượng đồ án theo "mã thể loại"
    int getQuantityByCategoryCode(String categoryCode);

    // Lấy ra số lượng đồ án theo "mã bộ môn"
    int getQuantityByDepartmentCode(String departmentCode);

    // Lấy ra số lượng đồ án theo "năm học"
    List<YearDTO> getQuantityByYears(int startYear, int endYear);

    // Thêm đồ án
    ProjectDTO addProject(ProjectDTO projectDTO);

    // Sửa đồ án
    void editProject(int id, ProjectDTO projectDTO);

    // Xoá đồ án
    void deleteProject(int id);

    // upload avatar
    ProjectDTO uploadAvatar(MultipartFile file, int id) throws IOException;

    // find by key
    List<ProjectDTO> findByKey(String key,String nameCategory);

    List<ProjectDTO> findByName(String key);
}
