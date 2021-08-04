package com.fita.project.controller;

import com.fita.project.dto.CategoryDTO;
import com.fita.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Lấy ra tất cả các thể loại
    @GetMapping("get-all")
    public ResponseEntity<?> getCategories() {
        try {
            return ResponseEntity.ok(categoryService.getCategories());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra thể loại theo "id"
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm thể loại
    @PostMapping("add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.addCategory(categoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa thể loại
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.editCategory(id, categoryDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xoá thể loại
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
