package com.example.controller.api;

import com.example.model.*;
import com.example.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/schedule")
@Tag(name = "Schedule API", description = "Операції з розкладом")
public class ScheduleRestController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassService classService;

    @Autowired
    private DayOfWeekService dayOfWeekService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @GetMapping("/teacher/{id}")
    @Operation(summary = "Отримати розклад для вчителя за ID")
    public Map<String, List<String>> getScheduleByTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        List<DayOfWeek> days = dayOfWeekService.findAllDayOfWeek();
        List<TeacherSubject> teacherSubjects = teacherSubjectService.findAllByTeacher(teacher);

        Map<String, List<String>> scheduleMap = new LinkedHashMap<>();

        for (DayOfWeek day : days) {
            List<String> lessons = new ArrayList<>(Collections.nCopies(7, ""));
            for (TeacherSubject teacherSubject : teacherSubjects) {
                List<Schedule> schedules = scheduleService.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, day);
                for (Schedule schedule : schedules) {
                    int index = schedule.getLessonNumber() - 1;
                    String subject = teacherSubject.getSubject().getName();
                    String className = schedule.getClasses().getName();
                    lessons.set(index, subject + " (" + className + " клас)");
                }
            }
            scheduleMap.put(day.getName(), lessons);
        }

        return scheduleMap;
    }

    @GetMapping("/class/{id}")
    @Operation(summary = "Отримати розклад для класу за ID")
    public Map<String, List<String>> getScheduleByClass(@PathVariable Long id) {
        Classes classes = classService.findClassById(id);
        List<DayOfWeek> days = dayOfWeekService.findAllDayOfWeek();
        Map<String, List<String>> scheduleMap = new LinkedHashMap<>();

        for (DayOfWeek day : days) {
            List<Schedule> schedules = scheduleService.findSchedulesByClassesAndDayOfWeek(classes, day);
            List<String> lessons = new ArrayList<>(Collections.nCopies(7, ""));
            for (Schedule schedule : schedules) {
                int index = schedule.getLessonNumber() - 1;
                String subject = schedule.getTeacherSubject().getSubject().getName();
                String teacherName = schedule.getTeacherSubject().getTeacher().getName();
                lessons.set(index, subject + " (" + teacherName + ")");
            }
            scheduleMap.put(day.getName(), lessons);
        }

        return scheduleMap;
    }
}
