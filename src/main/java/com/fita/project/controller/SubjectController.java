package com.fita.project.controller;

import com.fita.project.dto.SubjectDTO;
import com.fita.project.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/subjects/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    // Lấy ra tất cả các môn học
    @GetMapping("get-all")
    public ResponseEntity<?> getSubjects() {
        try {
            return ResponseEntity.ok(subjectService.getSubjects());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra môn học theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getSubject(@PathVariable int id) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
    // Lấy ra môn học theo mã môn học
    @GetMapping("get-by-subjectcode/{subjectcode}")
    public ResponseEntity<?> getSubjectBySubjectCode(@PathVariable String subjectcode) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectBySubjectCode(subjectcode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm môn học
    @PostMapping("add")
    public ResponseEntity<?> addSubject(@RequestBody SubjectDTO subjectDTO) {
        try {
            subjectService.addSubject(subjectDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa môn học
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editSubject(@PathVariable int id, @RequestBody SubjectDTO subjectDTO) {
        try {
            subjectService.editSubject(id, subjectDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xóa môn học
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable int id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
