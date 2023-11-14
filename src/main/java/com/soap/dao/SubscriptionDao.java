package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.soap.exceptions.DaoException;
import com.soap.models.Subscription;
import com.soap.util.DbUtils;

public class SubscriptionDao {

        private Connection conn = DbUtils.getConnection();

    public void createSubscription(Subscription sub) throws DaoException {
        String sql = "INSERT INTO subscriptions (subscriber, curator, is_active, approved_at, valid_until) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getSubscriber());
            stmt.setString(2, sub.getCurator());
            stmt.setBoolean(3, sub.getIsActive());
            stmt.setTimestamp(4, sub.getApprovedAt());
            stmt.setTimestamp(5, sub.getValidUntil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating subscription");
            throw new DaoException(e.getMessage());
        }
    }    

    public void deleteSubscription(Subscription sub) throws DaoException {
        String sql = "DELETE FROM subscriptions WHERE subscriber = ? AND curator = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getSubscriber());
            stmt.setString(2, sub.getCurator());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting subscription");
            throw new DaoException(e.getMessage());
        }
    }

    public void findSubscription(Subscription sub) throws DaoException {
        String sql = "SELECT * FROM subscriptions WHERE ";
        Integer paramsCount = 0;

        if (sub.getSubscriber() != null) {
            sql += "subscriber = ?";
            paramsCount++;
        } 
        
        if (sub.getCurator() != null) {
            if (paramsCount > 0) {
                sql += " AND ";
            }
            sql += "curator = ?";
        } 

        if (sub.getIsActive() != null) {
            if (paramsCount > 0) {
                sql += " AND ";
            }
            sql += "is_active = ?";
        }

        int i = 1;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (sub.getSubscriber() != null) {
                stmt.setString(i, sub.getSubscriber());
                i++;
            }

            if (sub.getCurator() != null) {
                stmt.setString(i, sub.getCurator());
                i++;
            }

            if (sub.getIsActive() != null) {
                stmt.setBoolean(i, sub.getIsActive());
                i++;
            }
            
            stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error finding subscription");
            throw new RuntimeException(e);
        }
    }
}
