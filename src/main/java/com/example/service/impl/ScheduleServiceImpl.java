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

   /* @Override
    public void saveSchedule(Schedule schedule) {
        List<Schedule> existingSchedules = scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(schedule.getTeacherSubject(), schedule.getDayOfWeek());
        for (Schedule existingSchedule : existingSchedules) {
            if (existingSchedule.getLessonTime().equals(schedule.getLessonTime()) && existingSchedule.getClasses().equals(schedule.getClasses())) {
                throw new RuntimeException("Цей урок вже існує в розкладі!");
            }
        }

        scheduleRepository.save(schedule);
    }*/

    @Override
    public void saveSchedule(Schedule schedule) {

        List<Schedule> existingSchedules = scheduleRepository.findAll();

        for (Schedule existingSchedule : existingSchedules) {

            boolean sameDay =
                    existingSchedule.getDayOfWeek().equals(schedule.getDayOfWeek());

            boolean sameTime =
                    existingSchedule.getLessonTime().equals(schedule.getLessonTime());

            // Перевірка класу
            boolean sameClass =
                    existingSchedule.getClasses().equals(schedule.getClasses());

            // Перевірка викладача
            boolean sameTeacher =
                    existingSchedule.getTeacherSubject().getTeacher()
                            .equals(schedule.getTeacherSubject().getTeacher());

            if (sameDay && sameTime && sameClass) {
                throw new RuntimeException(
                        "У цього класу вже є урок на цей час!"
                );
            }

            if (sameDay && sameTime && sameTeacher) {
                throw new RuntimeException(
                        "Викладач уже зайнятий у цей час!"
                );
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

    @Override
    public void updateSchedule(Long id, Schedule updatedSchedule) {

        Schedule currentSchedule =
                scheduleRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Розклад не знайдено"));

        List<Schedule> allSchedules = scheduleRepository.findAll();

        for (Schedule existingSchedule : allSchedules) {

            // пропускаємо сам запис, який редагуємо
            if (existingSchedule.getId().equals(id)) {
                continue;
            }

            boolean sameDay =
                    existingSchedule.getDayOfWeek()
                            .equals(updatedSchedule.getDayOfWeek());

            boolean sameTime =
                    existingSchedule.getLessonTime()
                            .equals(updatedSchedule.getLessonTime());

            boolean sameClass =
                    existingSchedule.getClasses()
                            .equals(updatedSchedule.getClasses());

            boolean sameTeacher =
                    existingSchedule.getTeacherSubject()
                            .getTeacher()
                            .equals(updatedSchedule.getTeacherSubject().getTeacher());

            if (sameDay && sameTime && sameClass) {

                throw new RuntimeException(
                        "У цього класу вже є урок на цей час!"
                );
            }

            if (sameDay && sameTime && sameTeacher) {

                throw new RuntimeException(
                        "Викладач уже зайнятий у цей час!"
                );
            }
        }

        currentSchedule.setLessonNumber(updatedSchedule.getLessonNumber());
        currentSchedule.setClasses(updatedSchedule.getClasses());
        currentSchedule.setTeacherSubject(updatedSchedule.getTeacherSubject());
        currentSchedule.setDayOfWeek(updatedSchedule.getDayOfWeek());
        currentSchedule.setLessonTime(updatedSchedule.getLessonTime());

        scheduleRepository.save(currentSchedule);
    }
}
