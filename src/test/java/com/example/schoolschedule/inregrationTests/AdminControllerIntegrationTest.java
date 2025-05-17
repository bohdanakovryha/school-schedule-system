/*
package com.example.schoolschedule.inregrationTests;

import com.example.model.*;
import com.example.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassesRepository classRepository;

    @BeforeEach
    void setup() {
        // Очистка всіх необхідних таблиць
        teacherRepository.deleteAll();
        subjectRepository.deleteAll();
        classRepository.deleteAll();
    }

    @Test
    void testAdminPanelLoadsSuccessfully() throws Exception {
        mockMvc.perform(get("/adminPanel"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPanel"))
                .andExpect(content().string(containsString("teachers")));
    }

    @Test
    void testAddTeacher() throws Exception {
        mockMvc.perform(post("/adminPanel/teacher/add")
                        .param("name", "Іван Іванов"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertEquals(1, teacherRepository.count());
    }

    @Test
    void testDeleteTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("Петро Петренко");
        teacher = teacherRepository.save(teacher);

        mockMvc.perform(delete("/adminPanel/teacher/delete/" + teacher.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertFalse(teacherRepository.findById(teacher.getId()).isPresent());
    }

    @Test
    void testAddSubject() throws Exception {
        mockMvc.perform(post("/adminPanel/subject/add")
                        .param("name", "Математика"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertEquals(1, subjectRepository.count());
    }

    @Test
    void testDeleteSubject() throws Exception {
        Subject subject = new Subject();
        subject.setName("Фізика");
        subject = subjectRepository.save(subject);

        mockMvc.perform(delete("/adminPanel/subject/delete/" + subject.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertFalse(subjectRepository.findById(subject.getId()).isPresent());
    }

    @Test
    void testAddClass() throws Exception {
        mockMvc.perform(post("/adminPanel/class/add")
                        .param("name", "10-Б"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertEquals(1, classRepository.count());
    }

    @Test
    void testDeleteClass() throws Exception {
        Classes schoolClass = new Classes();
        schoolClass.setName("11-А");
        schoolClass = classRepository.save(schoolClass);

        mockMvc.perform(delete("/adminPanel/class/delete/" + schoolClass.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminPanel"));

        Assertions.assertFalse(classRepository.findById(schoolClass.getId()).isPresent());
    }

    // Додаткові тести можна реалізувати аналогічно:
    // - schedule/add
    // - schedule/delete/{id}
    // - teacherSubject/add
    // - teacherSubject/delete/{id}
    // - day/add
    // - day/delete/{id}
    // - lessonTime/add
    // - lessonTime/delete/{id}
}
*/
