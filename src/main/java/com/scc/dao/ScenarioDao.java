package com.scc.dao;

import com.scc.model.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void addRecord(Ticket ticket) {
        String sql = "INSERT INTO ticket(attendee_name, ticket_tier, price, payment_method) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getAttendeeName());
            pstmt.setString(2, ticket.getTicketTier());
            pstmt.setDouble(3, ticket.getPrice());
            pstmt.setString(4, ticket.getPaymentMethod());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    // [2] View Records from Database
    public List<Ticket> getAllRecords() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT id, attendee_name, ticket_tier, price, payment_method FROM ticket";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getString("attendee_name"),
                        rs.getString("ticket_tier"),
                        rs.getDouble("price"),
                        rs.getString("payment_method")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
        return tickets;
    }
}