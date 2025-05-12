CREATE TABLE IF NOT EXISTS CLASSES (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS DAY_OF_WEEK (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS LESSON_TIME (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             time VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS SUBJECT (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL
    );


CREATE TABLE IF NOT EXISTS TEACHER (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS TEACHER_SUBJECT (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               teacher_id BIGINT,
                                               subject_id BIGINT,
                                               FOREIGN KEY (teacher_id) REFERENCES TEACHER(id),
    FOREIGN KEY (subject_id) REFERENCES SUBJECT(id)
    );

CREATE TABLE IF NOT EXISTS SCHEDULE (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        lesson_number INT NOT NULL,
                                        class_id BIGINT,
                                        day_of_week_id BIGINT,
                                        lesson_time_id BIGINT,
                                        teacher_subject_id BIGINT,
                                        FOREIGN KEY (teacher_subject_id) REFERENCES TEACHER_SUBJECT(id),
    FOREIGN KEY (class_id) REFERENCES CLASSES(id),
    FOREIGN KEY (day_of_week_id) REFERENCES DAY_OF_WEEK(id),
    FOREIGN KEY (lesson_time_id) REFERENCES LESSON_TIME(id)
    );

