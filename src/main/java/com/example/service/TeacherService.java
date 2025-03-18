package com.example.service;

import com.example.model.Schedule;
import com.example.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    //new
    Teacher getTeacherById(Long teacherId);
}
