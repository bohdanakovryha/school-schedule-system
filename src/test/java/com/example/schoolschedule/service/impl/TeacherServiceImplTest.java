package com.example.schoolschedule.service.impl;

import com.example.model.Teacher;
import com.example.repository.TeacherRepository;
import com.example.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private Teacher sampleTeacher;
    @InjectMocks
    private TeacherServiceImpl teacherService;

    @BeforeEach
    void setUp() {
        sampleTeacher = new Teacher();
        sampleTeacher.setId(1L);
        sampleTeacher.setName("Тетяна Михайлівна");
    }

    @Test
    void testGetTeachers_Success() {
        List<Teacher> teacherList = List.of(sampleTeacher);
        when(teacherRepository.findAll()).thenReturn(teacherList);

        List<Teacher> result = teacherService.getTeachers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Тетяна Михайлівна", result.get(0).getName());

        verify(teacherRepository, times(1)).findAll();
    }
    @Test
    void testGetTeacher_NotFound() {
        when(teacherRepository.findAll()).thenReturn(Collections.emptyList());

        List<Teacher> result = teacherService.getTeachers();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    void testGetTeacherById_Success() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(sampleTeacher));

        Teacher result = teacherService.getTeacherById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Тетяна Михайлівна", result.getName());

        verify(teacherRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTeacherById_NotFound() {
        when(teacherRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> teacherService.getTeacherById(2L));
        assertEquals("teacher not found", exception.getMessage());

        verify(teacherRepository, times(1)).findById(2L);
    }
}
