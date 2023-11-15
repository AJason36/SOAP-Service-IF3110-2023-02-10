package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.soap.exceptions.DaoException;
import com.soap.models.ResponseCode;
import com.soap.models.Subscription;
import com.soap.util.DbUtils;

public class SubscriptionDao {

    private Connection conn = DbUtils.getConnection();

    /**
     * Create a subscription to database
     * @param sub
     * @throws DaoException
     */
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
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }    

    /**
     * Delete a subscription from database
     * @param sub Subscription to be deleted, only subscriber and curator are used
     * @throws DaoException
     */
    public void deleteSubscription(Subscription sub) throws DaoException {
        String sql = "DELETE FROM subscriptions WHERE subscriber = ? AND curator = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getSubscriber());
            stmt.setString(2, sub.getCurator());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting subscription");
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }

    /**
     * Find subscriptions from database
     * @param sub Subscription to be found, only subscriber, curator and is_active are used
     * @return
     * @throws DaoException
     */
    public Subscription[] findSubscription(Subscription sub) throws DaoException {
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

            ResultSet result = stmt.executeQuery();
            Subscription[] subscriptions = new Subscription[result.getFetchSize()];
            i = 0;
            while (result.next()) {
                subscriptions[i] = new Subscription(
                    result.getString("subscriber"),
                    result.getString("curator"),
                    result.getTimestamp("approved_at"),
                    result.getBoolean("is_active"),
                    result.getTimestamp("valid_until")
                );
                i++;
            }
            return subscriptions;
        } catch (SQLException e) {
            System.out.println("Error finding subscription");
            throw new DaoException(sql, ResponseCode.SERVER_ERROR);
        }
    }
}
