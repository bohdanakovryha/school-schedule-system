package com.example.service;

import com.example.model.*;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();


    //scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
 /*   List<Schedule> findSchedulesByClassesAndDayOfWeek(List<String> classesFromDB, DayOfWeek dayOfWeek);*/


    //List<Schedule> schedulesForDay = scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);
    List<Schedule> findSchedulesByTeacherSubjectAndDayOfWeek(TeacherSubject teacherSubject, DayOfWeek dayOfWeek);

    //  result.addAll(scheduleRepository.findSchedulesByTeacherSubject(teacherSubject));
    List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject);

    //scheduleMonday.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
    List<Schedule> findSchedulesByClassesAndDayOfWeek (Classes classes, DayOfWeek dayOfWeek);

    //List<Schedule> result = scheduleRepository.findSchedulesByClasses(classesFromDB);
    List<Schedule> findSchedulesByClasses (Classes classes);
}
