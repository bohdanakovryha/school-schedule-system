package com.example.controller;
import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.DayOfWeek;

import java.util.*;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TeacherSubjectRepository teacherSubjectRepository;
    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    private LessonTimeRepository lessonTimeRepository;
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

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
                //List<Schedule> schedulesForDay = scheduleRepository.findSchedulesByTeacherSubjectAndDayOfWeek(teacherSubject, dayOfWeek);

                for (Schedule schedule : schedulesForDay) {
                    int lessonIndex = schedule.getLessonNumber() - 1;
                    //String lessonName = schedule.getTeacherSubject().getSubject().getName();
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
            //result.addAll(scheduleRepository.findSchedulesByTeacherSubject(teacherSubject));
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

        return "teachers.html";
    }

    @GetMapping("/classes")
    public String getScheduleByClass(Classes classes, Model model) {
        // Отримуємо клас із бази даних
        Classes classesFromDB = classService.findClassById(classes.getId());
        model.addAttribute("selectedClassesId", classes.getId());


        // Отримуємо список розкладу для конкретного класу
      /*  List<Schedule> testSchedule = scheduleRepository.findSchedulesByClasses(classesFromDB);*/

        // Отримуємо список днів тижня
        List<DayOfWeek> dayOfWeekList = dayOfWeekService.findAllDayOfWeek();

        // Ініціалізуємо списки розкладів для кожного дня тижня
        List<String> scheduleMonday = new ArrayList<>();
        List<String> scheduleTuesday = new ArrayList<>();
        List<String> scheduleWednesday = new ArrayList<>();
        List<String> scheduleThursday = new ArrayList<>();
        List<String> scheduleFriday = new ArrayList<>();

        // Проходимо по днях тижня
        for (DayOfWeek dayOfWeek : dayOfWeekList) {
            if (dayOfWeek.getName().equals("Понеділок")) {
                //scheduleMonday.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
                scheduleMonday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Вівторок")) {
                //.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
                scheduleTuesday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Середа")) {
               // scheduleWednesday.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
                scheduleWednesday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("Четвер")) {
               // scheduleThursday.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
                scheduleThursday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
            if (dayOfWeek.getName().equals("П’ятниця")) {
                //scheduleFriday.addAll(checkLessonNumber(scheduleRepository.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
                scheduleFriday.addAll(checkLessonNumber(scheduleService.findSchedulesByClassesAndDayOfWeek(classesFromDB, dayOfWeek)));
            }
        }

        // Отримуємо весь розклад для класу ПОТІМ, ДЕ РЕПОЗИТОРІЇ
        //List<Schedule> result = scheduleRepository.findSchedulesByClasses(classesFromDB);
        List<Schedule> result = scheduleService.findSchedulesByClasses(classesFromDB);

        // Отримуємо час уроків //ПОТІМ
        List<LessonTime> lessonTimeFromDB = lessonTimeService.findAll();

        // Додаємо дані до моделі
        model.addAttribute("lessonTime", lessonTimeFromDB);
        model.addAttribute("schedule", result);
        model.addAttribute("scheduleMonday", scheduleMonday);
        model.addAttribute("scheduleTuesday", scheduleTuesday);
        model.addAttribute("scheduleWednesday", scheduleWednesday);
        model.addAttribute("scheduleThursday", scheduleThursday);
        model.addAttribute("scheduleFriday", scheduleFriday);

        classesController.getClasses(model);

        return "classes.html";
    }
}