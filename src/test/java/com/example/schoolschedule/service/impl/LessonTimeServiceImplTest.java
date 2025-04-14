package com.example.schoolschedule.service.impl;

import com.example.model.LessonTime;
import com.example.repository.LessonTimeRepository;
import com.example.service.impl.LessonTimeServiceImpl;
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
class LessonTimeServiceImplTest {
    @Mock
    private LessonTimeRepository lessonTimeRepository;
    @Mock
    private LessonTime sampleLessonTime;
    @InjectMocks
    private LessonTimeServiceImpl lessonTimeService;

    @BeforeEach
    void setUp() {
        sampleLessonTime = new LessonTime();
        sampleLessonTime.setId(1L);
        sampleLessonTime.setTime("09:00 - 09:45");
    }

    @Test
    void testFindAll_Success() {
        List<LessonTime> lessonTimeList = Arrays.asList(sampleLessonTime);
        when(lessonTimeRepository.findAll()).thenReturn(lessonTimeList);

        List<LessonTime> result = lessonTimeService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("09:00 - 09:45", result.get(0).getTime());

        verify(lessonTimeRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_NotFound() {
        when(lessonTimeRepository.findAll()).thenReturn(Collections.emptyList());

        List<LessonTime> result = lessonTimeService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(lessonTimeRepository, times(1)).findAll();
    }
}
