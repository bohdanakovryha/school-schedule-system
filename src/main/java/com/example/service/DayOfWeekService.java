package com.example.service;

import com.example.model.DayOfWeek;
import java.util.List;

public interface DayOfWeekService{
    List<DayOfWeek> findAllDayOfWeek();

    void saveDayOfWeek(DayOfWeek dayOfWeek);

    void deleteDayOfWeekById(Long id);
}
