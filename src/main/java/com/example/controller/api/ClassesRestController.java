package com.example.controller.api;

import com.example.model.Classes;
import com.example.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Classes API", description = "Операції з класами")
@SecurityRequirement(name = "oauth2Scheme")
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
/*@RestController
@RequestMapping("/api/classes")
@SecurityRequirement(name = "oauth2Scheme")
public class ClassesRestController {

    private final ClassServiceImpl classService;

    @Autowired
    public ClassesRestController(ClassServiceImpl classService) {
        this.classService = classService;
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping
    public List<Classes> getClasses() {
        return classService.getClasses(); // JSON повертається
    }
}
*/