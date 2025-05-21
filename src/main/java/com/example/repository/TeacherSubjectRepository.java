package com.example.repository;

import com.example.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.TeacherSubject;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {

    TeacherSubject findByTeacherId(Long teacherId);

    TeacherSubject findByTeacher(Teacher teacher);

    List<TeacherSubject> findAllByTeacher(Teacher teacher);
}
