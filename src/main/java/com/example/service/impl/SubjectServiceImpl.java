package com.example.service.impl;

import com.example.model.Subject;
import com.example.repository.SubjectRepository;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void saveSubject(Subject subject) {
        if (subject.getName() == null || subject.getName().isEmpty()) {
            throw new IllegalArgumentException("Назва предмету не може бути порожньою");
        }
        subjectRepository.save(subject);
    }

    @Override
    public void deleteSubjectById(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException(("Subject with id " + id + " не існує."));
        }

        try {
            subjectRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Subject with id " + id + " already exists");
        }
    }
}
