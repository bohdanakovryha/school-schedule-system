package com.example.service.impl;

import com.example.model.LessonTime;
import com.example.repository.LessonTimeRepository;
import com.example.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
