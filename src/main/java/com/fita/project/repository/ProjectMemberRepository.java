//package com.fita.project.repository;
//
//import com.fita.project.repository.entity.ProjectMember;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
//    List<ProjectMember> findByProjectCode(String projectCode);
//
//    @Transactional
//    void deleteByProjectCode(String projectCode);
//}
