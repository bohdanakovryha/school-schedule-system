package com.example.repository;

import com.example.model.LessonTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LessonTimeRepository extends JpaRepository<LessonTime, Long> {
}
