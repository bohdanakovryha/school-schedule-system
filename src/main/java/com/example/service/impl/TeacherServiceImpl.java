package com.example.service.impl;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("teacher not found"));
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        if (teacher.getName() == null || teacher.getName().isEmpty()) {
            throw new IllegalArgumentException("Ім’я викладача не може бути порожнім");
        }
        teacherRepository.save(teacher);
    }


    @Override
    public void deleteTeacherById(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Викладач з ID " + id + " не існує");
        }

        try {
            teacherRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Неможливо видалити викладача — є пов’язані предмети або уроки");
        }
    }
}
