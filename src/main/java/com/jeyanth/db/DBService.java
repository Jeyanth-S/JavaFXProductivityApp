package com.jeyanth.db;

import com.jeyanth.config.ConfigLoader;
import com.jeyanth.model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DBService {
    static Properties props;

    public DBService() throws java.io.IOException {
        props = ConfigLoader.loadConfig();
    }

    private static final String URL = props.getProperty("db.url");
    private static final String USER = props.getProperty("db.user");
    private static final String PASS = props.getProperty("db.password");

    public static void saveTask(Task task) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO tasks (name, done, date) VALUES (?, ?, CURRENT_DATE)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getTask());
            stmt.setBoolean(2, task.isDone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ DB save failed: " + e.getMessage());
        }
    }

    public static void updateTaskStatus(String taskName, boolean isDone) {
        LocalDate today = LocalDate.now();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE tasks SET done = ? WHERE name = ? AND date = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, isDone);
            stmt.setString(2, taskName);
            stmt.setDate(3, Date.valueOf(today));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Failed to update task: " + e.getMessage());
        }
    }
}
