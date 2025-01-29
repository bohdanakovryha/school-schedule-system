package com.example.service;

import com.example.model.Classes;
import com.example.repository.ClassesRepository;
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


}


