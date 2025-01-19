-- Таблиця для класів
CREATE TABLE CLASS (
                       class_id INT PRIMARY KEY,
                       class_name VARCHAR(255)
);

-- Таблиця для днів тижня
CREATE TABLE DAYOFWEEK (
                           day_id INT PRIMARY KEY,
                           day_name VARCHAR(255)
);

-- Таблиця для часу уроку
CREATE TABLE LESSON_TIME (
                             lesson_time_id INT PRIMARY KEY,
                             time VARCHAR(255)
);

-- Таблиця для предметів
CREATE TABLE SUBJECTS (
                          subject_id INT PRIMARY KEY,
                          subject_name VARCHAR(255)
);

-- Таблиця для вчителів
CREATE TABLE TEACHERS (
                          teacher_id INT PRIMARY KEY,
                          teacher_name VARCHAR(255)
);

-- Таблиця для зв'язків вчителів та предметів
CREATE TABLE TEACHER_SUBJECT (
                                 teacher_subject_id INT PRIMARY KEY,
                                 teacher_id INT,
                                 subject_id INT,
                                 FOREIGN KEY (teacher_id) REFERENCES TEACHERS(teacher_id),
                                 FOREIGN KEY (subject_id) REFERENCES SUBJECTS(subject_id)
);

-- Таблиця для розкладу
CREATE TABLE SCHEDULE (
                          schedule_id INT PRIMARY KEY,
                          lesson_number INT,
                          class_id INT,
                          teacher_subject_id INT,
                          day_of_week_id INT,
                          lesson_time_id INT,
                          FOREIGN KEY (class_id) REFERENCES CLASS(class_id),
                          FOREIGN KEY (teacher_subject_id) REFERENCES TEACHER_SUBJECT(teacher_subject_id),
                          FOREIGN KEY (day_of_week_id) REFERENCES DAYOFWEEK(day_id),
                          FOREIGN KEY (lesson_time_id) REFERENCES LESSON_TIME(lesson_time_id)
);
