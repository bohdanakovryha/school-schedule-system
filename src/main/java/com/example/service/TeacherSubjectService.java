package com.example.service;

import com.example.model.Teacher;
import com.example.model.TeacherSubject;
import java.util.List;

public interface TeacherSubjectService {
    List<TeacherSubject> findAllByTeacher(Teacher teacher);

    List<TeacherSubject> getAll();

    void saveTeacherSubject(TeacherSubject ts);

    void deleteTeacherSubjectById(Long id);
}
