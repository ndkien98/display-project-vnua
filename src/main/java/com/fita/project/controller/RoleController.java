package com.fita.project.controller;

import com.fita.project.dto.RoleDTO;
import com.fita.project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/roles/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class RoleController {
    @Autowired
    private RoleService roleService;

    // Lấy ra tất cả các vai trò
    @GetMapping("get-all")
    public ResponseEntity<?> getRoles() {
        try {
            return ResponseEntity.ok(roleService.getRoles());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra vai trò theo "id"
    @GetMapping("get-by-id/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable int roleId) {
        try {
            return ResponseEntity.ok(roleService.getRoleById(roleId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra vai trò theo "tên vai trò"
    @GetMapping("get-by-role-name/{roleName}")
    public ResponseEntity<?> getRoleById(@PathVariable String roleName) {
        try {
            return ResponseEntity.ok(roleService.getRoleByRoleName(roleName));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Lấy ra tất cả các chức năng
    @GetMapping("get-functions")
    public ResponseEntity<?> getFunctions() {
        try {
            return ResponseEntity.ok(roleService.getFunctions());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Thêm vai trò
    @PostMapping("add")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {
        try {
            roleService.addRole(roleDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Sửa vai trò
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        try {
            roleService.editRole(id, roleDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }

    // Xoá vai trò
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.toString());
        }
    }
}
