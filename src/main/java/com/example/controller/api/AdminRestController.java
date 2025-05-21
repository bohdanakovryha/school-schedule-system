package com.example.controller.api;

import com.example.model.*;
import com.example.service.*;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RolesAllowed("ROLE_ADMIN")
public class AdminRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClassService classService;
    @Autowired
    private TeacherSubjectService teacherSubjectService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private DayOfWeekService dayOfWeekService;
    @Autowired
    private LessonTimeService lessonTimeService;

    @GetMapping("/overview")
    public AdminDataResponse getAdminData() {
        return new AdminDataResponse(
                teacherService.getTeachers(),
                subjectService.getSubjects(),
                classService.getClasses(),
                teacherSubjectService.getAll(),
                scheduleService.getAll(),
                dayOfWeekService.findAllDayOfWeek(),
                lessonTimeService.findAll()
        );
    }

    @PostMapping("/teacher")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
    }

    @DeleteMapping("/teacher/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
    }

    @PostMapping("/subject")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.saveSubject(subject);
    }

    @DeleteMapping("/subject/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubjectById(id);
    }

    @PostMapping("/class")
    public void addClass(@RequestBody Classes classes) {
        classService.saveClass(classes);
    }

    @DeleteMapping("/class/{id}")
    public void deleteClass(@PathVariable Long id) {
        classService.deleteClassById(id);
    }

    @PostMapping("/teacherSubject")
    public void addTeacherSubject(@RequestBody TeacherSubject ts) {
        teacherSubjectService.saveTeacherSubject(ts);
    }

    @DeleteMapping("/teacherSubject/{id}")
    public void deleteTeacherSubject(@PathVariable Long id) {
        teacherSubjectService.deleteTeacherSubjectById(id);
    }

    @PostMapping("/schedule")
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
    }

    @PostMapping("/day")
    public void addDay(@RequestBody DayOfWeek dayOfWeek) {
        dayOfWeekService.saveDayOfWeek(dayOfWeek);
    }

    @DeleteMapping("/day/{id}")
    public void deleteDay(@PathVariable Long id) {
        dayOfWeekService.deleteDayOfWeekById(id);
    }

    @PostMapping("/lessonTime")
    public void addLessonTime(@RequestBody LessonTime lessonTime) {
        lessonTimeService.saveLessonTime(lessonTime);
    }

    @DeleteMapping("/lessonTime/{id}")
    public void deleteLessonTime(@PathVariable Long id) {
        lessonTimeService.deleteLessonTimeById(id);
    }

    public record AdminDataResponse(
            List<Teacher> teachers,
            List<Subject> subjects,
            List<Classes> classes,
            List<TeacherSubject> teacherSubject,
            List<Schedule> schedule,
            List<DayOfWeek> dayOfWeek,
            List<LessonTime> lessonTime
    ) {}
}
