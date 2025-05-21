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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findSchedulesByTeacherSubjectAndDayOfWeek(TeacherSubject teacherSubject, DayOfWeek dayOfWeek) {
        return scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);
    }

    @Override
    public List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject) {
        return scheduleRepository.findSchedulesByTeacherSubject(teacherSubject);
    }

    @Override
    public List<Schedule> findSchedulesByClassesAndDayOfWeek(Classes classes, DayOfWeek dayOfWeek) {
        return scheduleRepository.findSchedulesByClassesAndDayOfWeek(classes, dayOfWeek);
    }

    @Override
    public List<Schedule> findSchedulesByClasses(Classes classes) {
        return scheduleRepository.findSchedulesByClasses(classes);
    }

    @Override
    public List<Schedule> getAll(){
        return scheduleRepository.findAll();
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        List<Schedule> existingSchedules = scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(schedule.getTeacherSubject(), schedule.getDayOfWeek());
        for (Schedule existingSchedule : existingSchedules) {
            if (existingSchedule.getLessonTime().equals(schedule.getLessonTime()) && existingSchedule.getClasses().equals(schedule.getClasses())) {
                throw new RuntimeException("Цей урок вже існує в розкладі!");
            }
        }

        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteScheduleById(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new RuntimeException("Розклад з ID " + scheduleId + " не існує");
        }

        try {
            scheduleRepository.deleteById(scheduleId);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Неможливо видалити розклад — є пов’язані дані");
        }
    }
}
