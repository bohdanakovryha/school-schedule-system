package com.example.service;

import com.example.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    Teacher getTeacherById(Long teacherId);

    void saveTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

}
