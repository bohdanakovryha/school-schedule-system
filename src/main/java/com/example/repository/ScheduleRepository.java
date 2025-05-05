package com.example.repository;

import com.example.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findSchedulesByTeacherSubject(TeacherSubject teacherSubject);

    List<Schedule> findSchedulesByClasses(Classes classes);

    List<Schedule> findSchedulesByTeacherSubjectAndDayOfWeek(TeacherSubject teacherSubject, DayOfWeek dayOfWeek);

    List<Schedule> findSchedulesByClassesAndDayOfWeek(Classes classes, DayOfWeek dayOfWeek);

}
