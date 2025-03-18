package com.example.service.impl;

import com.example.model.DayOfWeek;
import com.example.repository.DayOfWeekRepository;
import com.example.service.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
