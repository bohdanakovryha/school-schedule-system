package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Spring Data JPA автоматично створить методи для стандартних операцій, таких як findAll()
}
