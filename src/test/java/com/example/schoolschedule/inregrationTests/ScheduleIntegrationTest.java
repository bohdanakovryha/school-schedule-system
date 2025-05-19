/*
package com.example.schoolschedule.inregrationTests;

import com.example.SchoolScheduleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SchoolScheduleApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScheduleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // 1. Користувач може відкрити сторінку розкладу (HTTP 200)
    @Test
    public void schedulePageIsAccessible() throws Exception {
        mockMvc.perform(get("/schedule"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

    // 2. Розклад містить обов’язкові поля (перевірка через json або html — залежить від реалізації)
    // Тут приклад, якщо розклад повертається JSON (наприклад REST API)
    @Test
    public void scheduleContainsRequiredFields() throws Exception {
        mockMvc.perform(get("/api/schedule")) // припустимо, що є REST endpoint
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subjectName").exists())
                .andExpect(jsonPath("$[0].teacherName").exists())
                .andExpect(jsonPath("$[0].className").exists())
                .andExpect(jsonPath("$[0].lessonTime").exists())
                .andExpect(jsonPath("$[0].dayOfWeek").exists());
    }

    // 3. Адмін може оновити розклад (POST з валідними параметрами) — HTTP 200 або 3xx
    @Test
    public void adminCanUpdateSchedule() throws Exception {
        mockMvc.perform(post("/admin/schedule/update")
                        .param("className", "10A")
                        .param("subjectName", "Math")
                        .param("teacherName", "John Smith")
                        .param("lessonTime", "08:30")
                        .param("dayOfWeek", "MONDAY"))
                .andExpect(status().is3xxRedirection());
    }

    // 4. Оновлення розкладу без необхідних параметрів - повертає 400 (Bad Request)
    @Test
    public void updateScheduleMissingParamsReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/admin/schedule/update")
                        .param("className", "10A")) // бракує інших параметрів
                .andExpect(status().isBadRequest());
    }

    // 5. Запит на неіснуючий URL повертає 404
    @Test
    public void nonExistingUrlReturns404() throws Exception {
        mockMvc.perform(get("/non-existing-url"))
                .andExpect(status().isNotFound());
    }
}
*/
