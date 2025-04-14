package com.example.schoolschedule.service.impl;

import com.example.model.Teacher;
import com.example.model.TeacherSubject;
import com.example.repository.TeacherSubjectRepository;
import com.example.service.impl.TeacherSubjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherSubjectServiceImplTest {
    @Mock
    private TeacherSubjectRepository teacherSubjectRepository;
    @Mock
    private Teacher sampleTeacher;
    @Mock
    private TeacherSubject sampleTeacherSubject;
    @InjectMocks
    private TeacherSubjectServiceImpl teacherSubjectService;

    @BeforeEach
    void setUp() {
        sampleTeacher = new Teacher();
        sampleTeacherSubject = new TeacherSubject();
    }

    @Test
    void testFindAllByTeacher_Success() {
        List<TeacherSubject> teacherSubjectList = List.of(sampleTeacherSubject);
        when(teacherSubjectRepository.findAllByTeacher(sampleTeacher)).thenReturn(teacherSubjectList);

        List<TeacherSubject> result = teacherSubjectService.findAllByTeacher(sampleTeacher);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(teacherSubjectRepository, times(1)).findAllByTeacher(sampleTeacher);
    }

    @Test
    void testFindAllByTeacher_Fail() {
        when(teacherSubjectRepository.findAllByTeacher(sampleTeacher)).thenReturn(Collections.emptyList());

        List<TeacherSubject> result = teacherSubjectService.findAllByTeacher(sampleTeacher);

        assertNotNull(result);
        assertTrue(result.isEmpty()); // Але результат має бути порожній список

        verify(teacherSubjectRepository, times(1)).findAllByTeacher(sampleTeacher);
    }
}

