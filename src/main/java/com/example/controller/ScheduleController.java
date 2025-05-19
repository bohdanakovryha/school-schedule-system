package com.example.controller;
import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.DayOfWeek;

import java.util.*;

@Controller
@RequestMapping("/schedule")
@SecurityRequirement(name = "oauth2Scheme")
public class ScheduleController {
    @Autowired
    private TeacherController teacherController;
    @Autowired
    private ClassesController classesController;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherSubjectService teacherSubjectService;
    @Autowired
    private DayOfWeekService dayOfWeekService;
    @Autowired
    private ClassService classService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private LessonTimeService lessonTimeService;

     List<String> checkLessonNumber(List<Schedule> lessons) {
         List<String> result = new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " ", " "));

         for (Schedule schedule : lessons) {
             String subjectName = schedule.getTeacherSubject().getSubject().getName();
             String teacherName = schedule.getTeacherSubject().getTeacher().getName();

             String subjectWithTeacher = subjectName + " (" + teacherName + ")";

             result.set(schedule.getLessonNumber() - 1, subjectWithTeacher);
         }

         return result;
     }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/teacher")
    public String getScheduleByTeacher(Teacher teacher, Model model) {
        Teacher teacherFromDB = teacherService.getTeacherById(teacher.getId());
        model.addAttribute("selectedTeacherId", teacher.getId());

        List<TeacherSubject> teacherSubjectList = teacherSubjectService.findAllByTeacher(teacherFromDB);
        List<DayOfWeek> dayOfWeekList = dayOfWeekService.findAllDayOfWeek();

        List<String> scheduleMonday = new ArrayList<>(Collections.nCopies(7, "")); // 7 уроків на день
        List<String> scheduleTuesday = new ArrayList<>(Collections.nCopies(7, ""));
        List<String> scheduleWednesday = new ArrayList<>(Collections.nCopies(7, ""));
        List<String> scheduleThursday = new ArrayList<>(Collections.nCopies(7, ""));
        List<String> scheduleFriday = new ArrayList<>(Collections.nCopies(7, ""));

        for (TeacherSubject teacherSubject : teacherSubjectList) {
            for (DayOfWeek dayOfWeek : dayOfWeekList) {
                List<Schedule> schedulesForDay = scheduleService.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);

                for (Schedule schedule : schedulesForDay) {
                    int lessonIndex = schedule.getLessonNumber() - 1;
                    String lessonName = schedule.getTeacherSubject().getSubject().getName() +
                            " (" + schedule.getClasses().getName() + " клас)";

                    if (dayOfWeek.getName().equals("Понеділок")) {
                        scheduleMonday.set(lessonIndex, lessonName);
                    } else if (dayOfWeek.getName().equals("Вівторок")) {
                        scheduleTuesday.set(lessonIndex, lessonName);
                    } else if (dayOfWeek.getName().equals("Середа")) {
                        scheduleWednesday.set(lessonIndex, lessonName);
                    } else if (dayOfWeek.getName().equals("Четвер")) {
                        scheduleThursday.set(lessonIndex, lessonName);
                    } else if (dayOfWeek.getName().equals("П’ятниця")) {
                        scheduleFriday.set(lessonIndex, lessonName);
                    }
                }
            }
        }

        List<Schedule> result = new ArrayList<>();

        for (TeacherSubject teacherSubject : teacherSubjectList) {
            result.addAll(scheduleService.findSchedulesByTeacherSubject(teacherSubject));
        }

        List<LessonTime> lessonTimeFromDB = lessonTimeService.findAll();

        model.addAttribute("lessonTime", lessonTimeFromDB);
        model.addAttribute("schedule", result);
        model.addAttribute("scheduleMonday", scheduleMonday);
        model.addAttribute("scheduleTuesday", scheduleTuesday);
        model.addAttribute("scheduleWednesday", scheduleWednesday);
        model.addAttribute("scheduleThursday", scheduleThursday);
        model.addAttribute("scheduleFriday", scheduleFriday);

        teacherController.teacher(model);

        return "teachers";
    }

    @GetMapping("/classes")
    @PreAuthorize("hasRole('ADMIN')")
    public String getScheduleByClass(Classes classes, Model model) {
        Classes classesFromDB = classService.findClassById(classes.getId());
        model.addAttribute("selectedClassesId", classes.getId());

        List<DayOfWeek> dayOfWeekList = dayOfWeekService.findAllDayOfWeek();

        List<String> scheduleMonday = new ArrayList<>();
        List<String> scheduleTuesday = new ArrayList<>();
        List<String> scheduleWednesday = new ArrayList<>();
        List<String> scheduleThursday = new ArrayList<>();
        List<String> scheduleFriday = new ArrayList<>();

        for (DayOfWeek dayOfWeek : dayOfWeekList) {
            if (dayOfWeek.getName().equals("Понеділок")) {
                scheduleMonday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Вівторок")) {
                scheduleTuesday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Середа")) {
                scheduleWednesday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Четвер")) {
                scheduleThursday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("П’ятниця")) {
                scheduleFriday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
        }

        List<Schedule> result = scheduleService.findSchedulesByClasses(classesFromDB);

        List<LessonTime> lessonTimeFromDB = lessonTimeService.findAll();

        model.addAttribute("lessonTime", lessonTimeFromDB);
        model.addAttribute("schedule", result);
        model.addAttribute("scheduleMonday", scheduleMonday);
        model.addAttribute("scheduleTuesday", scheduleTuesday);
        model.addAttribute("scheduleWednesday", scheduleWednesday);
        model.addAttribute("scheduleThursday", scheduleThursday);
        model.addAttribute("scheduleFriday", scheduleFriday);

        classesController.getClasses(model);

        return "classes";
    }
}