package com.example.repository;

import com.example.model.Schedule;
import com.example.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    static List<Schedule> findByStudentIdAndDayOfWeek(Long studentId, String dayOfWeek){
        return findByStudentIdAndDayOfWeek(studentId, dayOfWeek);
    };

    static List<Schedule> findByTeacherIdAndDayOfWeek(Long teacherId, String attr0) {
        return findByTeacherIdAndDayOfWeek(teacherId, attr0);
    };

//    List<Teacher> findAllTeachers();
}
