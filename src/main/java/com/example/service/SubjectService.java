package com.example.service;

import com.example.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjects();

    void saveSubject(Subject subject);

    void deleteSubjectById(Long id);
}
