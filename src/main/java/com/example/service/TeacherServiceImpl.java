package com.example.service;

import com.example.model.Schedule;
import com.example.model.Teacher;
import com.example.repository.ScheduleRepository;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

}
