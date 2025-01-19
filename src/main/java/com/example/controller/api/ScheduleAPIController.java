package com.example.controller.api;

import com.example.model.Classes;
import com.example.model.Teacher;
import com.example.repository.ClassesRepository;
import com.example.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleAPIController {

    private final ClassesRepository classRepository;

    public ScheduleAPIController(ClassesRepository classRepository) {
        this.classRepository = classRepository;
    }

    // API для отримання списку класів
    @GetMapping("classes")
    public List<Classes> getClasses() {
        return classRepository.findAll();
    }
}

