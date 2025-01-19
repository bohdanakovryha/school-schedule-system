package com.example.service;

import com.example.model.Classes;
import com.example.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public List<String> getClasses() {
        List<Classes> classesFromDB = classesRepository.findAll();
        return classesFromDB.stream().map(Classes::getName).collect(Collectors.toList());
    }

    @Override
    public List<Classes> getClassesEntities() {
        // Якщо ви використовуєте об'єкти, переконайтесь, що цей метод працює коректно
        return List.of(new Classes("Class 1"), new Classes("Class 2"));
    }
}


