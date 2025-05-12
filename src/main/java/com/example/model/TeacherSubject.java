package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "TEACHER_SUBJECT")
public class TeacherSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Override
    public String toString() {
        return subject.getName() + " (" + teacher.getName() + ")";
    }

    @OneToMany(mappedBy = "teacherSubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules;

}
