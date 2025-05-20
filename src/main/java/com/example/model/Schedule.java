package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SCHEDULE")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lesson_number")
    private int lessonNumber;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne
    @JoinColumn(name = "teacher_subject_id")
    private TeacherSubject teacherSubject;

    @ManyToOne
    @JoinColumn(name = "day_of_week_id", referencedColumnName = "id")
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "lesson_time_id")
    private LessonTime lessonTime;

    public Schedule() {

    }

    public Teacher getTeacher() {
        return teacherSubject != null ? teacherSubject.getTeacher() : null;
    }

    public Schedule(Long id, Classes classes, TeacherSubject teacherSubject, DayOfWeek dayOfWeek, LessonTime lessonTime) {
        this.id = id;
        this.teacherSubject = teacherSubject;
        this.classes = classes;
        this.lessonTime = lessonTime;
        this.dayOfWeek = dayOfWeek;
    }
}
