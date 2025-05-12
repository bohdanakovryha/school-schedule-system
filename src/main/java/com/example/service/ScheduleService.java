package com.example.service;

import com.example.model.*;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();

    List<Schedule> findSchedulesByTeacherSubjectAndDayOfWeek(TeacherSubject teacherSubject, DayOfWeek dayOfWeek);

    List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject);

    List<Schedule> findSchedulesByClassesAndDayOfWeek (Classes classes, DayOfWeek dayOfWeek);

    List<Schedule> findSchedulesByClasses (Classes classes);

    List<Schedule> getAll();

    void saveSchedule(Schedule schedule);

    void deleteScheduleById(Long id);
}
