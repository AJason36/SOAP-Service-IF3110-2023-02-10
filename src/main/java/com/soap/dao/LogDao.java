package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.soap.exceptions.DaoException;
import com.soap.models.Log;
import com.soap.util.DbUtils;

public class LogDao {
    private Connection conn = DbUtils.getConnection();

    public void createLog(Log log) throws DaoException {
        String sql = "INSERT INTO request_log (description, ip_address, endpoint) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, log.getDescription());
            stmt.setString(2, log.getIpAddress());
            stmt.setString(3, log.getEndpoint());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error creating log");
            throw new DaoException(e.getMessage());
        }
    }
}
