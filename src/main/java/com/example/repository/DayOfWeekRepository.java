package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.DayOfWeek;
import org.springframework.stereotype.Repository;


@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {

}
