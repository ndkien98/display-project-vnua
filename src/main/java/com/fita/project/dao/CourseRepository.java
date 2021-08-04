package com.fita.project.dao;

import com.fita.project.repository.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByYearSemesterId(int yearSemesterId);
    List<Course> findByLecturerCode(String lecturerCode);
    List<Course> findByLecturerCodeAndYearSemesterId(String lecturerCode, int yearSemesterId);
}
