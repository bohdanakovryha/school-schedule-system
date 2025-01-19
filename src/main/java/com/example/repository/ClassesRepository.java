package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Class, Long> {
    List<Class> findAll();  // Оскільки JpaRepository вже має метод findAll(), можна його використовувати для отримання всіх класів.
}