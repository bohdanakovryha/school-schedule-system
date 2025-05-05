package com.example.controller.api;

import com.example.model.Classes;
import com.example.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Classes API", description = "Операції з класами")
public class ClassesRestController {

    @Autowired
    private ClassService classService;

    @GetMapping
    @Operation(summary = "Отримати всі класи")
    public List<Classes> getAllClasses() {
        return classService.getClasses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Отримати клас за ID")
    public Classes getClassById(@PathVariable Long id) {
        return classService.findClassById(id);
    }
}
