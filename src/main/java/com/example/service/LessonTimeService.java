package com.example.service;

import com.example.model.LessonTime;
import java.util.List;

public interface LessonTimeService {
    List<LessonTime> findAll();

    void saveLessonTime(LessonTime lessonTime);

    void deleteLessonTimeById(Long id);
}
