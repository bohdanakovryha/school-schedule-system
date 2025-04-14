package com.example.schoolschedule.service.impl;

import com.example.model.Classes;
import com.example.repository.ClassesRepository;
import com.example.service.impl.ClassServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassServiceImplTest {

    @Mock
    private ClassesRepository classesRepository;
    @Mock
    private Classes sampleClass;
    @InjectMocks
    private ClassServiceImpl classService;

    @BeforeEach
    void setUp() {
        sampleClass = new Classes();
        sampleClass.setId(1L);
        sampleClass.setName("1 клас");
    }

    @Test
    void testGetClasses_Success() {
        List<Classes> classList = Arrays.asList(sampleClass);
        when(classesRepository.findAll()).thenReturn(classList);

        List<Classes> result = classService.getClasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1 клас", result.get(0).getName());

        verify(classesRepository, times(1)).findAll();
    }

    @Test
    void testGetClasses_Failure() {
        when(classesRepository.findAll()).thenReturn(Collections.emptyList());

        List<Classes> result = classService.getClasses();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(classesRepository, times(1)).findAll();
    }

    @Test
    void testFindClassById_Success() {
        when(classesRepository.findById(1L)).thenReturn(Optional.of(sampleClass));

        Classes result = classService.findClassById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("1 клас", result.getName());

        verify(classesRepository, times(1)).findById(1L);
    }

    @Test
    void testFindClassById_NotFound() {
        when(classesRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> classService.findClassById(2L));
        assertEquals("class not found", exception.getMessage());

        verify(classesRepository, times(1)).findById(2L);
    }
}

