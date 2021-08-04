package com.fita.project.dao;

import com.fita.project.repository.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject findBySubjectCode(String subjectCode);
}
