package com.example.service;

import com.example.model.Schedule;
import com.example.model.Teacher;
import com.example.repository.ScheduleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Метод для отримання розкладу викладача по днях тижня
    public List<Schedule> getScheduleByTeacherAndDayOfWeek(Long teacherId, String dayOfWeek) {
        return ScheduleRepository.findByTeacherIdAndDayOfWeek(teacherId, dayOfWeek);
    }

    // Метод для отримання всіх викладачів
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
