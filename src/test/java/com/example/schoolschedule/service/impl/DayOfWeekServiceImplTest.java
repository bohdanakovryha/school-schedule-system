package com.example.schoolschedule.service.impl;
import com.example.model.DayOfWeek;
import com.example.repository.DayOfWeekRepository;
import com.example.service.impl.DayOfWeekServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DayOfWeekServiceImplTest {
    @Mock
    private DayOfWeekRepository dayOfWeekRepository;
    @Mock
    private DayOfWeek sampleDay;
    @InjectMocks
    private DayOfWeekServiceImpl dayOfWeekService;

    @BeforeEach
    void setUp() {
        sampleDay = new DayOfWeek();
        sampleDay.setId(1L);
        sampleDay.setName("Понеділок");
    }

    @Test
    void testFindAllDayOfWeek_Success() {
        List<DayOfWeek> dayList = Arrays.asList(sampleDay);
        when(dayOfWeekRepository.findAll()).thenReturn(dayList);

        List<DayOfWeek> result = dayOfWeekService.findAllDayOfWeek();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Понеділок", result.get(0).getName());

        verify(dayOfWeekRepository, times(1)).findAll();
    }

    @Test
    void testFindAllDayOfWeek_NotFound() {
        when(dayOfWeekRepository.findAll()).thenReturn(Collections.emptyList());

        List<DayOfWeek> result = dayOfWeekService.findAllDayOfWeek();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(dayOfWeekRepository, times(1)).findAll();
    }
}
