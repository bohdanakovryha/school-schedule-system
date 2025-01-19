package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_subject_id")
    private TeacherSubject teacherSubject;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes schoolClasses;

    @ManyToOne
    @JoinColumn(name = "lesson_time_id")
    private LessonTime lessonTime;

    @ManyToOne
    @JoinColumn(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;

}
