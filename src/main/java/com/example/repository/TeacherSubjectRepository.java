package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.TeacherSubject;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
}
