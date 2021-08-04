package com.fita.project.dao;

import com.fita.project.repository.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, String> {
    Lecturer findByLecturerCode(String lecturerCode);

    @Transactional
    void deleteByLecturerCode(String lecturerCode);
}
