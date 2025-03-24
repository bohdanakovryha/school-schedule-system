package com.example.service;

import com.example.model.LessonTime;

import java.util.List;

public interface LessonTimeService {

    //List<LessonTime> lessonTimeFromDB = lessonTimeRepository.findAll();

    List<LessonTime> findAll();

}
