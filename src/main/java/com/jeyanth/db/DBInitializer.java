package com.jeyanth.db;

import java.sql.*;
import java.util.Properties;

import com.jeyanth.config.ConfigLoader;


public class DBInitializer {
    static Properties props;

    public DBInitializer() throws java.io.IOException {
        props = ConfigLoader.loadConfig();
    }

    private static final String URL = props.getProperty("db.url");
    private static final String USER = props.getProperty("db.user");
    private static final String PASS = props.getProperty("db.password");
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL JDBC Driver not found!");
        }
    }


    public static void init() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS tasks (
                id SERIAL PRIMARY KEY,
                name TEXT NOT NULL,
                done BOOLEAN DEFAULT false,
                date DATE DEFAULT CURRENT_DATE
            );
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("✅ Task table initialized or already exists.");

        } catch (SQLException e) {
            System.err.println("❌ DB initialization failed: " + e.getMessage());
        }
    }
}
