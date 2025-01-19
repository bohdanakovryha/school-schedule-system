package com.example.service;

import com.example.model.Classes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    public List<String> getClasses() {
        // Переконайтесь, що цей метод правильно отримує та повертає список класів
        return List.of("Class 1", "Class 2", "Class 3");
    }

    public List<Classes> getClassesEntities() {
        // Якщо ви використовуєте об'єкти, переконайтесь, що цей метод працює коректно
        return List.of(new Classes("Class 1"), new Classes("Class 2"));
    }
}


