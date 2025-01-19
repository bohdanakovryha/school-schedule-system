package com.example.controller.api;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherAPIController {
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
