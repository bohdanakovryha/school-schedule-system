package com.example.service;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSeviceImpl implements TeacherService {
    @Autowired
    private TeacherService teacherService;

    @Override
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }
}
