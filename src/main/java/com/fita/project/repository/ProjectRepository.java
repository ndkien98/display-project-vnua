//package com.fita.project.repository;
//
//import com.fita.project.repository.entity.Project;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProjectRepository extends JpaRepository<Project, Integer> {
//    Project findByProjectCode(String projectCode);
//    List<Project> findByStatus(int status);
//    List<Project> findByStudentCode(String studentCode);
//    List<Project> findByCategoryCode(String categoryCode);
//    List<Project> findByCourseId(int courseId);
//
//    @Query("select p from Project p join Course c on p.courseId = c.id where c.yearSemesterId = :yearSemesterId")
//    List<Project> findByYearSemesterId(int yearSemesterId);
//
//    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode")
//    List<Project> findByLectureCode(String lecturerCode);
//
//    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode and p.status = :status")
//    List<Project> findByLectureCodeAndStatus(String lecturerCode, int status);
//
//    @Query("select p from Project p join Course c on p.courseId = c.id where c.lecturerCode = :lecturerCode and p.status = :status and c.yearSemesterId = :yearSemesterId")
//    List<Project> findByLectureCodeAndStatusAndYearSemesterId(String lecturerCode, int status, int yearSemesterId);
//
//    Integer countByCategoryCode(String categoryCode);
//
//    @Query("select count(p) from Project p join Course c on p.courseId = c.id join Subject s on c.subjectCode = s.subjectCode join Department d on d.departmentCode = s.departmentCode where s.departmentCode = :departmentCode")
//    Integer countByDepartmentCode(String departmentCode);
//
//    @Query("select count(p) from Project p join Course c on p.courseId = c.id join YearSemester ys on c.yearSemesterId = ys.id where ys.year = :year")
//    Integer countByYear(int year);
//}
