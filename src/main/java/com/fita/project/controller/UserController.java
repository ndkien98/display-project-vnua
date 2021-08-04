package com.fita.project.controller;

import com.fita.project.dto.LecturerDTO;
import com.fita.project.dto.StudentDTO;
import com.fita.project.dto.UserDTO;
import com.fita.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/users/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class UserController {
    @Autowired
    private UserService userService;

    //====================GET METHOD====================
    // Lấy ra tất cả các người dùng
    @GetMapping("get-all")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra người dùng theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra tất cả các giảng viên
    @GetMapping("get-lecturers")
    public ResponseEntity<?> getLecturers() {
        try {
            return ResponseEntity.ok(userService.getLecturers());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra tất cả các sinh viên
    @GetMapping("get-students")
    public ResponseEntity<?> getStudents() {
        try {
            return ResponseEntity.ok(userService.getStudents());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(userService.getStudents());
        }
    }

    @GetMapping("students-by-lecture")
    public ResponseEntity<?> getStudentByLecture() {
        try {
            return ResponseEntity.ok(userService.getStudentByLecture());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(userService.getStudentByLecture());
        }
    }

    // Lấy ra giảng viên theo "id"
    @GetMapping("get-lecturer-by-id/{id}")
    public ResponseEntity<?> getLecturerById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getLecturerById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra sinh viên theo "id"
    @GetMapping("get-student-by-id/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getStudentById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // lấy ra sinh viên theo user_code
    @GetMapping("get-student-by-user-code")
    public ResponseEntity<?> getStudentByUserCode(@Param("code") String code) {
        try {
            return ResponseEntity.ok(userService.getStudentByStudentCode(code));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }


    //====================POST METHOD====================
    // Thêm người dùng
    @PostMapping("add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.addUser(userDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm giảng viên
    @PostMapping("add-lecturer")
    public ResponseEntity<?> addLecturer(@RequestBody LecturerDTO lecturerDTO) {
        try {
            userService.addLecturer(lecturerDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm sinh viên
    @PostMapping("add-student")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            userService.addStudent(studentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }


    //====================PUT METHOD====================
    // Sửa người dùng
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        try {
            userService.editUser(id, userDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    @PutMapping("edit-avatar")
    public ResponseEntity<UserDTO> editImage(@Param("id") int id, @Param("file")MultipartFile file) {
        try {
            UserDTO userDTO =  userService.updateAvatar(file,id);
            return ResponseEntity.ok(userDTO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Sửa giảng viên
    @PutMapping("edit-lecturer/{id}")
    public ResponseEntity<?> editLecturer(@PathVariable int id, @RequestBody LecturerDTO lecturerDTO) {
        try {
            userService.editLecturer(id, lecturerDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa sinh viên
    @PutMapping("edit-student/{id}")
    public ResponseEntity<?> editStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        try {
            userService.editStudent(id, studentDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }


    //====================DELETE METHOD====================
    // Xoá người dùng
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xoá giảng viên
    @DeleteMapping("delete-lecturer/{id}")
    public ResponseEntity<?> deleteLecturer(@PathVariable int id) {
        try {
            userService.deleteLecturer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xoá sinh viên
    @DeleteMapping("delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        try {
            userService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }


    // Import Excel
    @PostMapping("/import-excel")
    public ResponseEntity<?> importExcelFileUser(@RequestParam("file") MultipartFile files) throws IOException {
        try {
            userService.createUserFile(files);
            return ResponseEntity.ok("successfull");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
