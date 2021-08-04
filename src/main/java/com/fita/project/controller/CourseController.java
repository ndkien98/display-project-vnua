package com.fita.project.controller;

import com.fita.project.dto.CourseDTO;
import com.fita.project.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/courses/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class CourseController {
    @Autowired
    private CourseService courseService;


    @PostMapping("file")
    public ResponseEntity<?> importFile(@RequestParam("file") MultipartFile file) throws IOException {
        courseService.importFile(file);
        return null;
    }

    // Lấy ra tất cả các lớp học phần
    @GetMapping("get-all")
    public ResponseEntity<?> getCourses() {
        try {
            return ResponseEntity.ok(courseService.getCourses());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra lớp học phần theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra lớp học phần theo "mã giảng viên"
    @GetMapping("get-by-lecturer-code/{lecturerCode}")
    public ResponseEntity<?> getCoursesByLecturerCode(@PathVariable String lecturerCode) {
        try {
            return ResponseEntity.ok(courseService.getCoursesByLecturerCode(lecturerCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra lớp học phần theo "năm học - học kỳ id"
    @GetMapping("get-by-year-semester-id/{yearSemesterId}")
    public ResponseEntity<?> getCoursesByYearSemesterId(@PathVariable int yearSemesterId) {
        try {
            return ResponseEntity.ok(courseService.getCoursesByYearSemesterId(yearSemesterId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra lớp học phần theo "mã giảng viên" và "năm học - học kỳ id"
    @GetMapping("get-by-lecturer-code/{lecturerCode}/year-semester-id/{yearSemesterId}")
    public ResponseEntity<?> getCoursesByLecturerCodeAndYearSemesterId(@PathVariable String lecturerCode, @PathVariable int yearSemesterId) {
        try {
            return ResponseEntity.ok(courseService.getCourses(lecturerCode, yearSemesterId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm lớp học phần
    @PostMapping("add")
    public ResponseEntity<?> addCourse(@RequestBody CourseDTO courseDTO) {
        try {
            courseService.addCourse(courseDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    //Sửa lớp học phần
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO) {
        try {
            courseService.editCourse(id, courseDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xóa lớp học phần
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
