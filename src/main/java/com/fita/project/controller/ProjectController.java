package com.fita.project.controller;

import com.fita.project.dto.ProjectDTO;
import com.fita.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/projects/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // Lấy ra tất cả các đồ án
    @GetMapping("get-all")
    public ResponseEntity<?> getProjects() {
        try {
            return ResponseEntity.ok(projectService.getProjects());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    @GetMapping("find-by-key")
    public ResponseEntity<?> findByKey(@RequestParam("nProject") String key,@RequestParam("nCategory") String ame){
        try {
            return ResponseEntity.ok(projectService.findByKey(key,ame));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    @GetMapping("find-by-name")
    public ResponseEntity<?> findByKey(@RequestParam("key") String key){
        try {
            return ResponseEntity.ok(projectService.findByName(key));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "trạng thái"
    @GetMapping("get-by-status/{status}")
    public ResponseEntity<?> getProjectsByStatus(@PathVariable int status) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByStatus(status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(projectService.getProjectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã đồ án"
    @GetMapping("get-by-project-code/{projectCode}")
    public ResponseEntity<?> getProjectByProjectCode(@PathVariable String projectCode) {
        try {
            return ResponseEntity.ok(projectService.getProjectByProjectCode(projectCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã sinh viên"
    @GetMapping("get-by-student-code/{studentCode}")
    public ResponseEntity<?> getProjectsByStudentCode(@PathVariable String studentCode) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByStudentCode(studentCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã thể loại"
    @GetMapping("get-by-category-code/{categoryCode}")
    public ResponseEntity<?> getProjectsByCategoryId(@PathVariable String categoryCode) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByCategoryCode(categoryCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã giảng viên"
    @GetMapping("get-by-lecturer-code/{lecturerCode}")
    public ResponseEntity<?> getProjectsByLecturerCode(@PathVariable String lecturerCode) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByLecturerCode(lecturerCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "lớp học phần id"
    @GetMapping("get-by-course-id/{courseId}")
    public ResponseEntity<?> getProjectsByCourseId(@PathVariable int courseId) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByCourseId(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "năm học - học kỳ id"
    @GetMapping("get-by-year-semester-id/{yearSemesterId}")
    public ResponseEntity<?> getProjectByProjectCode(@PathVariable int yearSemesterId) {
        try {
            return ResponseEntity.ok(projectService.getProjectsByYearSemesterId(yearSemesterId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã giảng viên + trạng thái"
    @GetMapping("get-by-lecturer-code/{lecturerCode}/status/{status}")
    public ResponseEntity<?> getProjects(@PathVariable String lecturerCode, @PathVariable int status) {
        try {
            return ResponseEntity.ok(projectService.getProjects(lecturerCode, status));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra đồ án theo "mã giảng viên + trạng thái + năm học - học kỳ id"
    @GetMapping("get-by-lecturer-code/{lecturerCode}/status/{status}/year-semester-id/{yearSemesterId}")
    public ResponseEntity<?> getProjects(@PathVariable String lecturerCode, @PathVariable int status, @PathVariable int yearSemesterId) {
        try {
            return ResponseEntity.ok(projectService.getProjects(lecturerCode, status, yearSemesterId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra số lượng đồ án theo "mã thể loại"
    @GetMapping("get-quantity-by-category-code/{categoryCode}")
    public ResponseEntity<?> getQuantityByCategoryCode(@PathVariable String categoryCode) {
        try {
            return ResponseEntity.ok(projectService.getQuantityByCategoryCode(categoryCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra số lượng đồ án theo "mã bộ môn"
    @GetMapping("get-quantity-by-department-code/{departmentCode}")
    public ResponseEntity<?> getQuantityByDepartmentCode(@PathVariable String departmentCode) {
        try {
            return ResponseEntity.ok(projectService.getQuantityByDepartmentCode(departmentCode));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra số lượng đồ án theo "năm học"
    @GetMapping("get-quantity-by-years")
    public ResponseEntity<?> getQuantityByYear(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear) {
        try {
            return ResponseEntity.ok(projectService.getQuantityByYears(startYear, endYear));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm đồ án
    @PostMapping("add")
    public ResponseEntity<?> addProject(@RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO projectDTO1 = projectService.addProject(projectDTO);
            return ResponseEntity.ok().body(projectDTO1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa đồ án
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editProject(@PathVariable int id, @RequestBody ProjectDTO projectDTO) {
        try {
            projectService.editProject(id, projectDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // upload avatar
    @PutMapping("edit/upload-avatar")
    public ResponseEntity<ProjectDTO> uploadAvatar(@Param("id") int id, @Param("file")MultipartFile file) {
        try {
            ProjectDTO projectDTO = this.projectService.uploadAvatar(file,id);
            return ResponseEntity.ok().body(projectDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Xoá đồ án
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable int id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
