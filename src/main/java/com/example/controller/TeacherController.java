package com.example.controller;

import com.example.model.Teacher;
import com.example.service.TeacherService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String teacher(Model model) {
        List<Teacher> allTeachers = teacherService.getTeachers();
        model.addAttribute("teachers", allTeachers);
        return "teachers";
    }
}
