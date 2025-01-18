package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Class;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByName(String name);
}
