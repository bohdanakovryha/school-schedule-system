package com.example.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Конструктор, який приймає параметр типу String
    public Classes(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    // Геттер для name
    public String getName() {
        return name;
    }

    // Сеттер для name (якщо потрібно)
    public void setName(String name) {
        this.name = name;
    }

    // Перевизначення методу toString для зручності виведення
    @Override
    public String toString() {
        return name;
    }
}
