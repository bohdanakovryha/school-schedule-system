package com.example.controller;

import com.example.model.Teacher;
import com.example.repository.ClassesRepository;
import com.example.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ClassesRepository classRepository;
    private final TeacherRepository teacherRepository;

    public ScheduleController(ClassesRepository classRepository, TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }

    // API для отримання списку класів
    @GetMapping("/classes")
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    // API для отримання списку викладачів
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
}

