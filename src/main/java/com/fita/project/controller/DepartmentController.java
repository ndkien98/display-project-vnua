package com.fita.project.controller;

import com.fita.project.dto.DepartmentDTO;
import com.fita.project.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/departments/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // Lấy ra tất cả các bộ môn
    @GetMapping("get-all")
    public ResponseEntity<?> getDepartments() {
        try {
            return ResponseEntity.ok(departmentService.getDepartments());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra bộ môn theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable int id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm bộ môn
    @PostMapping("add")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentService.addDepartment(departmentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa bộ môn
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable int id, @RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentService.editDepartment(id, departmentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xoá bộ môn
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
