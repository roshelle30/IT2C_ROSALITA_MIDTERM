package com.scc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:scenario.db";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS ticket (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    attendee_name TEXT NOT NULL,\n" +
                "    ticket_tier TEXT NOT NULL,\n" +
                "    price REAL NOT NULL,\n" +
                "    payment_method TEXT NOT NULL\n" +
                ");";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}