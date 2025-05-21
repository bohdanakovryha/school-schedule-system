package com.example.service.impl;

import com.example.model.DayOfWeek;
import com.example.repository.DayOfWeekRepository;
import com.example.service.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayOfWeekServiceImpl implements DayOfWeekService {
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @Override
    public List<DayOfWeek> findAllDayOfWeek() {
        return dayOfWeekRepository.findAll();
    }

    @Override
    public void saveDayOfWeek(DayOfWeek dayOfWeek) {
        dayOfWeekRepository.save(dayOfWeek);
    }

    @Override
    public void deleteDayOfWeekById(Long dayOfWeekId) {
        if (!dayOfWeekRepository.existsById(dayOfWeekId)) {
            throw new RuntimeException("Day of week with id " + dayOfWeekId + " не існує");
        }

        try {
            dayOfWeekRepository.deleteById(dayOfWeekId);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Неможливо видалити день тижня — пов’язано з розкладом");
        }
    }
}
