package com.example.controller;

import com.example.model.Class;
import com.example.model.Schedule;
import com.example.model.Teacher;
import com.example.repository.ClassRepository;
import com.example.repository.ScheduleRepository;
import com.example.repository.TeacherRepository;
import com.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /*@GetMapping("/schedule/teacher")
    public String getTeacherSchedule(@RequestParam Long teacherId, @RequestParam String dayOfWeek, Model model) {
        // Отримання розкладу для вибраного викладача та дня тижня
        List<Schedule> schedule = scheduleService.getScheduleByTeacherAndDayOfWeek(teacherId, dayOfWeek);
        model.addAttribute("schedule", schedule);

        // Отримання списку викладачів для випадаючого списку
        List<Teacher> teachers = scheduleService.getAllTeachers();
        model.addAttribute("teachers", teachers);

        return "teacher_schedule";
    }*/
   /* @GetMapping("/schedule/student")
    public String getSchedule(@RequestParam("studentId") Long studentId, @RequestParam("dayOfWeek") String dayOfWeek) {
        // Your logic here to fetch the schedule for the student
        return "Schedule for student with ID " + studentId + " on " + dayOfWeek;
    }*/
    // Для головної сторінки
    // Інжектуємо репозиторії через @Autowired
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    // Метод для відображення даних на головній сторінці
    @GetMapping("/")
    public String index(Model model) {
        // Отримуємо список всіх викладачів та класів з бази даних
        List<Teacher> teachers = teacherRepository.findAll();
        List<Class> classes = classRepository.findAll();

        // Додаємо списки до моделі
        model.addAttribute("teachers", teachers);
        model.addAttribute("classes", classes);

        return "index";  // Головна сторінка (наприклад, index.html)
    }

    // Для сторінки студентського розкладу
    // Розклад студента
    @GetMapping("/schedule/student")
    public String getStudentSchedule(@RequestParam Long studentId, @RequestParam String dayOfWeek, Model model) {
        // Тут має бути логіка для отримання розкладу студента з бази даних
        // Наприклад:
        List<Schedule> studentSchedule = ScheduleRepository.findByStudentIdAndDayOfWeek(studentId, dayOfWeek);

        // Після отримання даних передаємо їх в модель
        model.addAttribute("studentSchedule", studentSchedule);
        return "student_schedule"; // Сторінка, де відображається розклад студента
    }

    // Для сторінки вчительського розкладу
    @GetMapping("/teacher_schedule")
    public String teacherSchedule() {
        return "teacher_schedule";  // Завантажить teacher_schedule.html
    }
}
