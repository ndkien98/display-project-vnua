//package com.fita.project.repository;
//
//import com.fita.project.repository.entity.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//
//@Repository
//public interface StudentRepository extends JpaRepository<Student, String> {
//    Student findByStudentCode(String studentCode);
//
//    @Transactional
//    void deleteByStudentCode(String studentCode);
//}
