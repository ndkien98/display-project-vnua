package com.fita.project.services;

import com.fita.project.dto.SubjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    // Lấy ra tất cả các môn học
    List<SubjectDTO> getSubjects();

    // Lấy ra môn học theo "id"
    SubjectDTO getSubjectById(int id);

    // Lấy ra môn học theo "mã môn học"
    SubjectDTO getSubjectBySubjectCode(String subjectCode);

    // Thêm môn học
    void addSubject(SubjectDTO subjectDTO);

    // Sửa môn học
    void editSubject(int id, SubjectDTO subjectDTO);

    // Xóa môn học
    void deleteSubject(int id);
}
