package com.fita.project.dao;

import com.fita.project.repository.entity.YearSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearSemesterRepository extends JpaRepository<YearSemester, Integer> {
}
