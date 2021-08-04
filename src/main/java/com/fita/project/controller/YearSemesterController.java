package com.fita.project.controller;

import com.fita.project.dto.YearSemesterDTO;
import com.fita.project.services.YearSemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/years-semesters/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class YearSemesterController {
    @Autowired
    private YearSemesterService yearSemesterService;

    // Lấy ra tất cả các năm học - học kỳ
    @GetMapping("get-all")
    public ResponseEntity<?> getYearsSemesters() {
        try {
            return ResponseEntity.ok(yearSemesterService.getYearsSemesters());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra năm học - học kỳ theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getYearSemester(@PathVariable int id) {
        try {
            return ResponseEntity.ok(yearSemesterService.getYearSemesterById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm năm học - học kỳ
    @PostMapping("add")
    public ResponseEntity<?> addYearSemester(@RequestBody YearSemesterDTO yearSemesterDTO) {
        try {
            yearSemesterService.addYearSemester(yearSemesterDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    //Sửa năm học - học kỳ
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editYearSemester(@PathVariable int id, @RequestBody YearSemesterDTO yearSemesterDTO) {
        try {
            yearSemesterService.editYearSemester(id, yearSemesterDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xóa năm học - học kỳ
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteYearSemester(@PathVariable int id) {
        try {
            yearSemesterService.deleteYearSemester(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
