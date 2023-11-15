package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soap.exceptions.DaoException;
import com.soap.models.ResponseCode;
import com.soap.models.Subscription;
import com.soap.models.builders.SubscriptionBuilder;
import com.soap.util.DbUtils;

public class SubscriptionDao {

    private Connection conn = DbUtils.getConnection();
    private SubscriptionBuilder subBuilder = new SubscriptionBuilder();

    /**
     * Create a subscription to database
     * @param sub
     * @throws DaoException
     */
    public void createSubscription(Subscription sub) throws DaoException {
        String sql = "INSERT INTO subscription (subscriber, curator, is_active, approved_at, valid_until) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getSubscriber());
            stmt.setString(2, sub.getCurator());
            stmt.setBoolean(3, sub.getIsActive());
            stmt.setTimestamp(4, DbUtils.gregorianXMLToTimestamp(sub.getApprovedAt()));
            stmt.setTimestamp(5, DbUtils.gregorianXMLToTimestamp(sub.getValidUntil()));
            stmt.executeUpdate();
        } catch (Exception e) {
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
        String sql = "DELETE FROM subscription WHERE subscriber = ? AND curator = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getSubscriber());
            stmt.setString(2, sub.getCurator());
            stmt.executeUpdate();
        } catch (Exception e) {
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
        String sql = "SELECT * FROM subscription WHERE ";
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
            List<Subscription> subscriptions = new ArrayList<Subscription>();
            i = 0;
            while (result.next()) {
                subscriptions.add(
                    subBuilder.setSubscriber(result.getString("subscriber"))
                        .setCurator(result.getString("curator"))
                        .setIsActive(result.getBoolean("is_active"))
                        .setApprovedAt(result.getTimestamp("approved_at"))
                        .setValidUntil(result.getTimestamp("valid_until"))
                        .create()
                );
                i++;
            }
            return subscriptions.toArray(new Subscription[subscriptions.size()]);
        } catch (Exception e) {
            System.out.println("Error finding subscription");
            throw new DaoException(sql, ResponseCode.SERVER_ERROR);
        }
    }
}
