package com.example.service.impl;

import com.example.model.Classes;
import com.example.repository.ClassesRepository;
import com.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public List<Classes> getClasses() {
        return classesRepository.findAll();
    }

    @Override
    public Classes findClassById(Long classId) {
        return classesRepository.findById(classId).orElseThrow(() -> new RuntimeException("class not found"));
    }
}


