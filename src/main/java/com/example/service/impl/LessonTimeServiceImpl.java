package com.example.service.impl;

import com.example.model.LessonTime;
import com.example.repository.LessonTimeRepository;
import com.example.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    @Autowired
    private LessonTimeRepository lessonTimeRepository;

    @Override
    public List<LessonTime> findAll() {
        return lessonTimeRepository.findAll();
    }

    @Override
    public void saveLessonTime(LessonTime lessonTime) {
        lessonTimeRepository.save(lessonTime);
    }

    @Override
    public void deleteLessonTimeById(Long lessonTimeId) {
        if(!lessonTimeRepository.existsById(lessonTimeId)) {
            throw new RuntimeException(("Lesson time with id " + lessonTimeId + " already exists"));
        }

        try{
            lessonTimeRepository.deleteById(lessonTimeId);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Lesson time with id " + lessonTimeId + " already exists");
        }
    }
}
