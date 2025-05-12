package com.example.service.impl;

import com.example.model.Classes;
import com.example.repository.ClassesRepository;
import com.example.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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

    /*@Override
    public void saveClass(Classes classes) {
        classesRepository.save(classes);
    }*/

    @Override
    public void saveClass(Classes classes) {
        if (classes.getName() == null || classes.getName().isEmpty()) {
            throw new IllegalArgumentException("Назва класу не може бути порожньою");
        }
        classesRepository.save(classes);
    }


    @Override
    public void deleteClassById(Long classId) {
        if(!classesRepository.existsById(classId)) {
            throw new RuntimeException(("Class with id " + classId + " already exists"));
        }

        try{
            classesRepository.deleteById(classId);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Class with id " + classId + " already exists");
        }
    }
}


