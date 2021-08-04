package com.fita.project.dao;

import com.fita.project.repository.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByProjectCode(String projectCode);
    List<Project> findByStatus(int status);
    List<Project> findByStudentCode(String studentCode);
    List<Project> findByCategoryCode(String categoryCode);
    List<Project> findByCourseId(int courseId);

    List<Project> findAllByStatus(int status);

    @Query("select p from Project p join Course c on p.courseId = c.id where c.yearSemesterId = :yearSemesterId and p.status = 1")
    List<Project> findByYearSemesterId(int yearSemesterId);

    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode and p.status = 1")
    List<Project> findByLectureCode(String lecturerCode);

    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode and p.status = :status and p.status = 1")
    List<Project> findByLectureCodeAndStatus(String lecturerCode, int status);

    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode and p.status = :status and c.yearSemesterId = :yearSemesterId and p.status = 1")
    List<Project> findByLectureCodeAndStatusAndYearSemesterId(String lecturerCode, int status, int yearSemesterId);

    Integer countByCategoryCode(String categoryCode);

    @Query("select count(p) from Project p join Course c on p.courseId = c.id join Subject s on c.subjectCode = s.subjectCode join Department d on d.departmentCode = s.departmentCode where s.departmentCode = :departmentCode and p.status = 1")
    Integer countByDepartmentCode(String departmentCode);

    @Query("select count(p) from Project p join Course c on p.courseId = c.id join YearSemester ys on c.yearSemesterId = ys.id where ys.year = :year and p.status = 1")
    Integer countByYear(int year);

    @Query(value = "select * from projects where (project_name like %:key%) and (category_code in (select categories.category_code from categories where category_name like %:key2%)) and status = 1", nativeQuery = true)
    List<Project> findByKey(@Param("key") String key,@Param("key2") String key2);

    @Query(value = "select * from projects where (project_name like %:key%) or (category_code in (select categories.category_code from categories where category_name like %:key%)) and status = 1", nativeQuery = true)
    List<Project> findByNameOrNameCategory(@Param("key") String key);
}
