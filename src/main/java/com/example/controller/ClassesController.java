package com.example.controller;

import com.example.model.Classes;
import com.example.service.impl.ClassServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/classes")
@SecurityRequirement(name = "oauth2Scheme")
public class ClassesController {

    private final ClassServiceImpl classService;

    @Autowired
    public ClassesController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @GetMapping
    public String getClasses(Model model) {
        List<Classes> classesName = classService.getClasses();
        model.addAttribute("classes", classesName);
        return "classes";
    }

}

