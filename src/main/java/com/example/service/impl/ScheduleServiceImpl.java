package com.example.service.impl;

import com.example.model.Classes;
import com.example.model.DayOfWeek;
import com.example.model.Schedule;
import com.example.model.TeacherSubject;
import com.example.repository.ScheduleRepository;
import com.example.service.ScheduleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Метод для отримання всіх викладачів
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findSchedulesByTeacherSubjectAndDayOfWeek(TeacherSubject teacherSubject, DayOfWeek dayOfWeek) {
        return scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);
    }
//scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);
    @Override
    public List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject) {
        return scheduleRepository.findSchedulesByTeacherSubject(teacherSubject);
    }

    @Override
    public List<Schedule> findSchedulesByClassesAndDayOfWeek(Classes classes, DayOfWeek dayOfWeek) {
        return scheduleRepository.findSchedulesByClassesAndDayOfWeek(classes, dayOfWeek);
    }

    //List<Schedule> result = scheduleRepository.findSchedulesByClasses(classesFromDB);
    @Override
    public List<Schedule> findSchedulesByClasses(Classes classes) {
        return scheduleRepository.findSchedulesByClasses(classes);
    }
//List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject);

}
