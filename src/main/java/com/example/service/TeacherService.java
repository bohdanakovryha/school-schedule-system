package com.example.service;

import com.example.model.Schedule;
import com.example.model.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getTeachers();

    /*//new
    List<Schedule> getScheduleByTeacher(Long teacherId);*/
}
