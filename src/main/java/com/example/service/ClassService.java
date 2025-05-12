package com.example.service;

import com.example.model.Classes;
import java.util.List;

public interface ClassService {
    List<Classes> getClasses();

    Classes findClassById(Long classId);

    void saveClass(Classes classes);

    void deleteClassById(Long id);
}
