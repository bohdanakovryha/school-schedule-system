package com.example.controller;

import com.example.model.Classes;
import com.example.service.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassesController {

    private final ClassServiceImpl classService;

    @Autowired
    public ClassesController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @GetMapping
    public String getClasses(Model model) {
        List<String> classesName = classService.getClasses();  // Переконайтеся, що цей метод повертає правильний список класів
        model.addAttribute("classes", classesName);
        return "classes.html";  // Переконайтеся, що файл шаблону classes.html існує
    }

    @GetMapping("/api/classes")
    @ResponseBody
    public List<Classes> getClassesApi() {
        return classService.getClassesEntities();  // Переконайтеся, що цей метод повертає правильні дані
    }
}

