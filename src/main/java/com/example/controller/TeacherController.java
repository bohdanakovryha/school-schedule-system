package com.example.controller;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    /*@Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }
*/
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll(); // Витягнути всіх викладачів з бази даних
    }
}
