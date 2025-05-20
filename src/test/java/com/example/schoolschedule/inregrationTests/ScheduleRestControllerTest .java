/*
package com.example.schoolschedule.inregrationTests;

import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ScheduleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LessonTimeRepository lessonTimeRepository;

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    private ClassService classService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private LessonTimeService lessonTimeService;

    @Autowired
    private DayOfWeekService dayOfWeekService;

    @BeforeEach
    void setup() {
        scheduleRepository.deleteAll();
        teacherRepository.deleteAll();
        classesRepository.deleteAll();
        subjectRepository.deleteAll();
        lessonTimeRepository.deleteAll();
        dayOfWeekRepository.deleteAll();

        Teacher teacher1 = new Teacher();
        teacher1.setName("John Smith");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Jane Doe");
        teacherRepository.save(teacher2);

        Subject math = new Subject();
        math.setName("Mathematics");
        subjectRepository.save(math);

        Subject physics = new Subject();
        physics.setName("Physics");
        subjectRepository.save(physics);

        TeacherSubject teacherSubject1 = new TeacherSubject();
        teacherSubject1.setTeacher(teacher1);
        teacherSubject1.setSubject(math);

        Classes class1 = new Classes();
        class1.setName("1A");
        classesRepository.save(class1);

        Classes class2 = new Classes();
        class2.setName("2B");
        classesRepository.save(class2);

        LessonTime time1 = new LessonTime();
        time1.setTime("08:30 - 09:15");
        lessonTimeRepository.save(time1);

        LessonTime time2 = new LessonTime();
        time2.setTime("09:25 - 10:10");
        lessonTimeRepository.save(time2);

        DayOfWeek monday = new DayOfWeek();
        monday.setName("Monday");
        dayOfWeekRepository.save(monday);

        DayOfWeek tuesday = new DayOfWeek();
        tuesday.setName("Tuesday");
        dayOfWeekRepository.save(tuesday);

        scheduleRepository.save(new Schedule(null, class1, teacherSubject1, monday, time1));
        scheduleRepository.save(new Schedule(null, class1, teacherSubject1, monday, time2));
        scheduleRepository.save(new Schedule(null, class2, teacherSubject1, tuesday, time1));

    }


    @Test
    void accessSchedulePage_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/schedule"))
                .andExpect(status().isOk());
    }

    @Test
    void studentSeesClassSchedule_shouldReturnCorrectData() throws Exception {
        mockMvc.perform(get("/api/schedule/class/" + getClassIdByName("1A")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject.name").value("Mathematics"))
                .andExpect(jsonPath("$[0].teacher.name").value("John Smith"))
                .andExpect(jsonPath("$[0].className").value("1A"))
                .andExpect(jsonPath("$[0].lessonTime.time").value("08:30 - 09:15"))
                .andExpect(jsonPath("$[0].dayOfWeek.name").value("Monday"));
    }

    @Test
    void teacherSeesOwnSchedule_shouldReturnCorrectData() throws Exception {
        mockMvc.perform(get("/api/schedule/teacher/" + getTeacherIdByName("Jane Doe")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject.name").value("Physics"))
                .andExpect(jsonPath("$[0].teacher.name").value("Jane Doe"))
                .andExpect(jsonPath("$[0].lessonTime.time").value("08:30 - 09:15"));
    }

    @Test
    void adminCanUpdateSchedule_shouldReturnOk() throws Exception {
        Long scheduleId = scheduleRepository.findAll().get(0).getId();

        String updatedJson = """
        {
          "id": %d,
          "subject": {"id": %d, "name": "Physics"},
          "teacher": {"id": %d, "name": "Jane Doe"},
          "class": {"id": %d, "name": "1A"},
          "lessonTime": {"id": %d, "time": "08:30 - 09:15"},
          "dayOfWeek": {"id": %d, "name": "Monday"}
        }
        """.formatted(
                scheduleId,
                getSubjectIdByName("Physics"),
                getTeacherIdByName("Jane Doe"),
                getClassIdByName("1A"),
                getLessonTimeIdByTime("08:30 - 09:15"),
                getDayOfWeekIdByName("Monday")
        );

        mockMvc.perform(put("/api/schedule/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk());
    }

    private Long getClassIdByName(String name) {
        return classService.getClasses().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Class not found: " + name))
                .getId();
    }

    private Long getTeacherIdByName(String name) {
        return teacherService.getTeachers().stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + name))
                .getId();
    }

    public Long getSubjectIdByName(String name) {
        return subjectService.getSubjects().stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subject not found: " + name))
                .getId();
    }

    public Long getLessonTimeIdByTime(String time)  {
        return lessonTimeService.findAll().stream()
                .filter(lt -> lt.getTime().equals(time))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("LessonTime not found: " + time))
                .getId();
    }

    public Integer getDayOfWeekIdByName(String name) {
        return dayOfWeekService.findAllDayOfWeek().stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("DayOfWeek not found: " + name))
                .getId();
    }
}
*/
