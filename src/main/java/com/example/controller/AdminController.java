package com.example.controller;

import com.example.model.*;
import com.example.service.*;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/adminPanel")
public class AdminController {

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

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("teachers", teacherService.getTeachers());
        model.addAttribute("subjects", subjectService.getSubjects());
        model.addAttribute("classes", classService.getClasses());
        model.addAttribute("teacherSubject", teacherSubjectService.getAll());
        model.addAttribute("schedule", scheduleService.getAll());
        model.addAttribute("dayOfWeek", dayOfWeekService.findAllDayOfWeek());
        model.addAttribute("lessonTime", lessonTimeService.findAll());
        return "adminPanel";
    }

    @PostMapping("/teacher/add")
    public String addTeacher(@ModelAttribute Teacher teacher, Model model) {
        try {
            teacherService.saveTeacher(teacher);
            return "redirect:/adminPanel";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "adminPanel";
        }
    }

    @DeleteMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            teacherService.deleteTeacherById(id);
            redirectAttributes.addFlashAttribute("success", "Викладача успішно видалено");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/subject/add")
    public String addSubject(@ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        try {
            subjectService.saveSubject(subject);
            return "redirect:/adminPanel";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/adminPanel";
        }
    }

    @DeleteMapping("/subject/delete/{id}")
    public String deleteSubject(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            subjectService.deleteSubjectById(id);
            redirectAttributes.addFlashAttribute("success", "Предмет успішно видалено");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/class/add")
    public String addClass(@ModelAttribute Classes classes, RedirectAttributes redirectAttributes) {
        try {
            classService.saveClass(classes);
            return "redirect:/adminPanel";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/adminPanel";
        }
    }

    @DeleteMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            classService.deleteClassById(id);
            redirectAttributes.addFlashAttribute("success", "Клас успішно видалено");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/teacherSubject/add")
    public String addTeacherSubject(@ModelAttribute TeacherSubject ts) {
        teacherSubjectService.saveTeacherSubject(ts);
        return "redirect:/adminPanel";
    }

    @DeleteMapping("/teacherSubject/delete/{id}")
    public String deleteTeacherSubject(@PathVariable Long id, Model model) {
        try {
            teacherSubjectService.deleteTeacherSubjectById(id);
            addAllAtributes(model);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Не можна видалити — пов’язано з уроками");
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/schedule/add")
    public String addSchedule(@ModelAttribute Schedule schedule, RedirectAttributes redirectAttributes) {
        try {
            scheduleService.saveSchedule(schedule);
            return "redirect:/adminPanel";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/adminPanel";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не вдалося додати розклад. Перевірте коректність даних або зв’язки.");
            return "redirect:/adminPanel";
        }
    }

    @DeleteMapping("/schedule/delete/{id}")
    public String deleteSchedule(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            scheduleService.deleteScheduleById(id);
            addAllAtributes(model);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/day/add")
    public String addDay(@ModelAttribute DayOfWeek dayOfWeek, RedirectAttributes redirectAttributes) {
        try {
            dayOfWeekService.saveDayOfWeek(dayOfWeek);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не вдалося додати день тижня");
            return "redirect:/adminPanel";
        }
        return "redirect:/adminPanel";
    }

    @DeleteMapping("/day/delete/{id}")
    public String deleteDay(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            dayOfWeekService.deleteDayOfWeekById(id);
            addAllAtributes(model);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/adminPanel";
    }


    @PostMapping("/lessonTime/add")
    public String addLessonTime(@ModelAttribute LessonTime lessonTime) {
        lessonTimeService.saveLessonTime(lessonTime);
        return "redirect:/adminPanel";
    }

    @DeleteMapping("/lessonTime/delete/{id}")
    public String deleteLessonTime(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            lessonTimeService.deleteLessonTimeById(id);
            addAllAtributes(model);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Не можна видалити час уроку — використовується у розкладі");
        }
        return "redirect:/adminPanel";
    }

    @PostMapping("/schedule/update/{id}")
    public String updateSchedule(@PathVariable Long id,
                                 @ModelAttribute Schedule schedule,
                                 RedirectAttributes redirectAttributes) {

        try {
            scheduleService.updateSchedule(id, schedule);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/adminPanel";
    }

    private String addAllAtributes(Model model) {
        model.addAttribute("teachers", teacherService.getTeachers());
        model.addAttribute("subjects", subjectService.getSubjects());
        model.addAttribute("classes", classService.getClasses());
        model.addAttribute("teacherSubject", teacherSubjectService.getAll());
        model.addAttribute("schedule", scheduleService.getAll());
        model.addAttribute("dayOfWeek", dayOfWeekService.findAllDayOfWeek());
        model.addAttribute("lessonTime", lessonTimeService.findAll());
        return "adminPanel";
    }
}
