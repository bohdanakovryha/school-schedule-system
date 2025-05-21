/*
package com.example.schoolschedule.integration;

import com.example.model.*;
import com.example.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.properties")
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ScheduleApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;
    @Autowired
    private LessonTimeRepository lessonTimeRepository;

    private Teacher teacher;
    private Subject subject;
    private Classes classes;
    private TeacherSubject teacherSubject;
    private DayOfWeek monday;
    private LessonTime lessonTime;
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        scheduleRepository.deleteAll();
        teacherSubjectRepository.deleteAll();
        teacherRepository.deleteAll();
        subjectRepository.deleteAll();
        classesRepository.deleteAll();
        dayOfWeekRepository.deleteAll();
        lessonTimeRepository.deleteAll();

        teacher = new Teacher();
        teacher.setName("John Smith");
        teacher = teacherRepository.save(teacher);

        subject = new Subject();
        subject.setName("Math");
        subject = subjectRepository.save(subject);

        classes = new Classes();
        classes.setName("10A");
        classes = classesRepository.save(classes);

        teacherSubject = new TeacherSubject();
        teacherSubject.setTeacher(teacher);
        teacherSubject.setSubject(subject);
        teacherSubject = teacherSubjectRepository.save(teacherSubject);

        monday = new DayOfWeek();
        monday.setName("Monday");
        monday = dayOfWeekRepository.save(monday);

        lessonTime = new LessonTime();
        lessonTime.setTime("08:00-08:45");
        lessonTime = lessonTimeRepository.save(lessonTime);

        schedule = new Schedule();
        schedule.setTeacherSubject(teacherSubject);
        schedule.setClasses(classes);
        schedule.setDayOfWeek(monday);
        schedule.setLessonTime(lessonTime);
        schedule.setLessonNumber(1);
        schedule = scheduleRepository.save(schedule);
    }

    @Test
    void testGetScheduleByTeacher_Positive() throws Exception {
        mockMvc.perform(get("/api/schedule/teacher/" + teacher.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Monday[0]").value("Math (10A клас)"));
    }

    @Test
    void testGetScheduleByTeacher_Negative() throws Exception {
        mockMvc.perform(get("/api/schedule/teacher/9999"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetScheduleByClass_Positive() throws Exception {
        mockMvc.perform(get("/api/schedule/class/" + classes.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Monday[0]").value("Math (John Smith)"));
    }

    @Test
    void testGetScheduleByClass_Negative() throws Exception {
        mockMvc.perform(get("/api/schedule/class/9999"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetAllTeachers() throws Exception {
        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Smith"));
    }

    @Test
    void testGetTeacherById_Positive() throws Exception {
        mockMvc.perform(get("/api/teachers/" + teacher.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"));
    }

    @Test
    void testGetTeacherById_Negative() throws Exception {
        mockMvc.perform(get("/api/teachers/9999"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetAllClasses() throws Exception {
        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("10A"));
    }

    @Test
    void testGetClassById_Positive() throws Exception {
        mockMvc.perform(get("/api/classes/" + classes.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("10A"));
    }

    @Test
    void testGetClassById_Negative() throws Exception {
        mockMvc.perform(get("/api/classes/9999"))
                .andExpect(status().isInternalServerError());
    }
}
*/
