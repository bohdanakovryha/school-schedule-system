package com.example.service.impl;

import com.example.model.Teacher;
import com.example.model.TeacherSubject;
import com.example.repository.TeacherSubjectRepository;
import com.example.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Override
    public List<TeacherSubject> findAllByTeacher(Teacher teacher) {
        return teacherSubjectRepository.findAllByTeacher(teacher);
    }

    @Override
    public List<TeacherSubject> getAll() {
        return teacherSubjectRepository.findAll();
    }

    @Override
    public void saveTeacherSubject(TeacherSubject teacherSubject) {
        if (teacherSubject.getTeacher() == null || teacherSubject.getSubject() == null) {
            throw new IllegalArgumentException("Викладач і предмет не можуть бути порожніми");
        }
        teacherSubjectRepository.save(teacherSubject);
    }

    @Override
    public void deleteTeacherSubjectById(Long teacherSubjectId) {
        if(!teacherSubjectRepository.existsById(teacherSubjectId)) {
            throw new RuntimeException(("Teacher_Subject with id " + teacherSubjectId + " already exists"));
        }

        try{
            teacherSubjectRepository.deleteById(teacherSubjectId);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Teacher_Subject with id " + teacherSubjectId + " already exists");
        }
    }
}
