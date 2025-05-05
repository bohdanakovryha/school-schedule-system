package com.example.repository;

import com.example.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.TeacherSubject;

import java.util.List;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {

    TeacherSubject findByTeacherId(Long teacherId);
    TeacherSubject findByTeacher(Teacher teacher);

    List<TeacherSubject> findAllByTeacher(Teacher teacher);
}
