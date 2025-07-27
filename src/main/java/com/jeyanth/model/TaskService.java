package com.jeyanth.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jeyanth.config.ConfigLoader;

public class TaskService {
    static Properties props;

    public TaskService() throws java.io.IOException {
        props = ConfigLoader.loadConfig();
    }

    private static final String URL = props.getProperty("db.url");
    private static final String USER = props.getProperty("db.user");
    private static final String PASS = props.getProperty("db.password");

    private static final String[] DEFAULT_TASKS = {
        "Social media (Facebook, X, Instagram)",
        "LinkedIn",
        "Leetcode",
        "Nextgen (College app)",
        "YouTube",
        "Duolingo",
        "GeeksforGeeks",
        "Message",
        "call home"
    };

    public static List<Task> getTasksForToday() {
        LocalDate today = LocalDate.now();
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // Ensure default tasks exist for today
            for (String taskName : DEFAULT_TASKS) {
                addTaskIfMissing(conn, taskName, today);
            }

            String sql = "SELECT name, done FROM tasks WHERE date = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(today));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tasks.add(new Task(rs.getString("name"), rs.getBoolean("done")));
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to load today's tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static void addTaskIfMissing(Connection conn, String taskName, LocalDate today) throws SQLException {
        String checkSql = "SELECT 1 FROM tasks WHERE name = ? AND date = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, taskName);
        checkStmt.setDate(2, Date.valueOf(today));
        ResultSet rs = checkStmt.executeQuery();

        if (!rs.next()) {
            String insertSql = "INSERT INTO tasks (name, done, date) VALUES (?, false, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, taskName);
            insertStmt.setDate(2, Date.valueOf(today));
            insertStmt.executeUpdate();
        }
    }

    public static void updateTaskStatus(String taskName, boolean done) {
        LocalDate today = LocalDate.now();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE tasks SET done = ? WHERE name = ? AND date = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, done);
            stmt.setString(2, taskName);
            stmt.setDate(3, Date.valueOf(today));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Failed to update task status: " + e.getMessage());
        }
    }
}
