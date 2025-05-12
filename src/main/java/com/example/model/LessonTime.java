package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "LESSON_TIME")
public class LessonTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;

    @OneToMany(mappedBy = "lessonTime", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules;

}
