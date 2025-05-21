/*
package com.example.schoolschedule.integration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDataSourceIsH2() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String url = metaData.getURL();
            System.out.println("Database URL: " + url);
            assertTrue(url.startsWith("jdbc:h2"), "Datasource is not H2!");
        }
    }
}*/
