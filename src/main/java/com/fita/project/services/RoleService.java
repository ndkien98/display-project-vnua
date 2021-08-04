package com.fita.project.services;

import com.fita.project.dto.FunctionDTO;
import com.fita.project.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    // Lấy ra tất cả các vai trò
    List<RoleDTO> getRoles();

    // Lấy ra vai trò theo "id"
    RoleDTO getRoleById(int id);

    // Lấy ra vai trò theo "tên vai trò"
    RoleDTO getRoleByRoleName(String roleName);

    // Lấy ra tất cả các chức năng
    List<FunctionDTO> getFunctions();

    // Lấy ra chức năng theo "id"
    FunctionDTO getFunctionById(int functionId);

    // Thêm vai trò
    void addRole(RoleDTO roleDTO);

    // Sửa vai trò
    void editRole(int id, RoleDTO roleDTO);

    // Xoá vai trò
    void deleteRole(int id);
}
