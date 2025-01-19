package com.example.controller;

import com.example.model.Classes;
import com.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    private final ClassService classService;

    @Autowired
    public ClassesController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public String getClasses(Model model) {
        List<String> classes = classService.getClasses();  // Переконайтеся, що цей метод повертає правильний список класів
        model.addAttribute("classes", classes);
        return "classes.html";  // Переконайтеся, що файл шаблону classes.html існує
    }

    @GetMapping("/api/classes")
    @ResponseBody
    public List<Classes> getClassesApi() {
        return classService.getClassesEntities();  // Переконайтеся, що цей метод повертає правильні дані
    }
}

