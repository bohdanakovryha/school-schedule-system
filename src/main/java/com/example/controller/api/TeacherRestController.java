package com.example.controller.api;

import com.example.model.Teacher;
import com.example.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teachers API", description = "Операції з викладачами")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    @Operation(summary = "Отримати всіх викладачів")
    public List<Teacher> getAllTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Отримати викладача за ID")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }
}
