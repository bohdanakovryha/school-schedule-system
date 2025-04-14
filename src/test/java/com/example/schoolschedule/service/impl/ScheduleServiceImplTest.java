package com.example.schoolschedule.service.impl;

import com.example.model.DayOfWeek;
import com.example.model.Schedule;
import com.example.model.TeacherSubject;
import com.example.model.Classes;
import com.example.repository.ScheduleRepository;
import com.example.service.impl.ScheduleServiceImpl;
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
class ScheduleServiceImplTest {
    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private Schedule sampleSchedule;
    @Mock
    private TeacherSubject sampleTeacherSubject;
    @Mock
    private DayOfWeek sampleDayOfWeek;
    @Mock
    private Classes sampleClass;
    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @BeforeEach
    void setUp() {
        sampleTeacherSubject = new TeacherSubject();
        sampleDayOfWeek = new DayOfWeek();
        sampleClass = new Classes();
        sampleSchedule = new Schedule();
    }

    @Test
    void testGetAllSchedules_Success() {
        List<Schedule> scheduleList = Arrays.asList(sampleSchedule);
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        List<Schedule> result = scheduleService.getAllSchedules();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void testGetAllSchedules_Empty() {
        when(scheduleRepository.findAll()).thenReturn(Collections.emptyList());

        List<Schedule> result = scheduleService.getAllSchedules();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    void testFindSchedulesByTeacherSubjectAndDayOfWeek_Success() {
        List<Schedule> scheduleList = Arrays.asList(sampleSchedule);
        when(scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek)).thenReturn(scheduleList);

        List<Schedule> result = scheduleService.findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek);
    }

    @Test
    void testFindSchedulesByTeacherSubjectAndDayOfWeek_Empty() {
        when(scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek)).thenReturn(Collections.emptyList());

        List<Schedule> result = scheduleService.findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(scheduleRepository, times(1)).findSchedulesByTeacherSubjectAndDayOfWeek(sampleTeacherSubject, sampleDayOfWeek);
    }

    @Test
    void testFindSchedulesByTeacherSubject_Success() {
        List<Schedule> scheduleList = Arrays.asList(sampleSchedule);
        when(scheduleRepository.findSchedulesByTeacherSubject(sampleTeacherSubject)).thenReturn(scheduleList);

        List<Schedule> result = scheduleService.findSchedulesByTeacherSubject(sampleTeacherSubject);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findSchedulesByTeacherSubject(sampleTeacherSubject);
    }

    @Test
    void testFindSchedulesByTeacherSubject_Empty() {
        when(scheduleRepository.findSchedulesByTeacherSubject(sampleTeacherSubject)).thenReturn(Collections.emptyList());

        List<Schedule> result = scheduleService.findSchedulesByTeacherSubject(sampleTeacherSubject);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(scheduleRepository, times(1)).findSchedulesByTeacherSubject(sampleTeacherSubject);
    }

    @Test
    void testFindSchedulesByClassesAndDayOfWeek_Success() {
        List<Schedule> scheduleList = Arrays.asList(sampleSchedule);
        when(scheduleRepository.findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek)).thenReturn(scheduleList);

        List<Schedule> result = scheduleService.findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek);
    }

    @Test
    void testFindSchedulesByClassesAndDayOfWeek_Empty() {
        when(scheduleRepository.findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek)).thenReturn(Collections.emptyList());

        List<Schedule> result = scheduleService.findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(scheduleRepository, times(1)).findSchedulesByClassesAndDayOfWeek(sampleClass, sampleDayOfWeek);
    }

    @Test
    void testFindSchedulesByClasses_Success() {
        List<Schedule> scheduleList = Arrays.asList(sampleSchedule);
        when(scheduleRepository.findSchedulesByClasses(sampleClass)).thenReturn(scheduleList);

        List<Schedule> result = scheduleService.findSchedulesByClasses(sampleClass);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduleRepository, times(1)).findSchedulesByClasses(sampleClass);
    }

    @Test
    void testFindSchedulesByClasses_Empty() {
        when(scheduleRepository.findSchedulesByClasses(sampleClass)).thenReturn(Collections.emptyList());

        List<Schedule> result = scheduleService.findSchedulesByClasses(sampleClass);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(scheduleRepository, times(1)).findSchedulesByClasses(sampleClass);
    }
}
