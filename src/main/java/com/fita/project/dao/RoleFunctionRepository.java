package com.fita.project.dao;

import com.fita.project.repository.entity.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleFunctionRepository extends JpaRepository<RoleFunction, Integer> {
    List<RoleFunction> findByRoleId(int roleId);

    @Transactional
    void deleteByRoleId(int roleId);

    @Transactional
    @Modifying
    @Query("update RoleFunction rf set rf.status = :status where rf.roleId = :roleId and rf.functionId = :functionId")
    void updateStatus(int roleId, int functionId, int status);
}
