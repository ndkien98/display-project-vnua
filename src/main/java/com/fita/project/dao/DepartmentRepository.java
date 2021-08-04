package com.fita.project.dao;

import com.fita.project.repository.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Department findDepartmentByDepartmentCode(String code);
    Department findDepartmentByDepartmentName(String name);
}
