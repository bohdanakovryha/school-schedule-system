package com.example.service.impl;

import com.example.model.Teacher;
import com.example.model.TeacherSubject;
import com.example.repository.TeacherSubjectRepository;
import com.example.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;
    @Override
    public List<TeacherSubject> findAllByTeacher(Teacher teacher) {
        return teacherSubjectRepository.findAllByTeacher(teacher);
    }
}
