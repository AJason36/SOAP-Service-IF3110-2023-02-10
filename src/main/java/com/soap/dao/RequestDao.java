package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.soap.exceptions.DaoException;
import com.soap.models.ResponseCode;
import com.soap.models.SubRequest;
import com.soap.util.DbUtils;

public class RequestDao {

    private Connection conn = DbUtils.getConnection();

    /**
     * Create a request to database
     * @param req 
     * @throws DaoException
     */
    public void createRequest(SubRequest req) throws DaoException {
        String sql = "INSERT INTO requests (requester, requestee, requester_email, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, req.getRequester());
            stmt.setString(2, req.getRequestee());
            stmt.setString(3, req.getRequesterEmail());
            stmt.setTimestamp(4, req.getCreatedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating request");
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }    

    /**
     * Delete a request from database
     * @param req Request to be deleted, only requester and requestee are used
     * @throws DaoException
     */
    public void deleteRequest(SubRequest req) throws DaoException {
        String sql = "DELETE FROM requests WHERE requester = ? AND requestee = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, req.getRequester());
            stmt.setString(2, req.getRequestee());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting request");
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }

    /**
     * Find requests from database
     * @param req Request to be found, only requester and requestee are used
     * @return
     * @throws DaoException
     */
    public SubRequest[] findRequest(SubRequest req) throws DaoException {
        String sql = "SELECT * FROM requests WHERE ";
        Integer paramsCount = 0;

        if (req.getRequester() != null) {
            sql += "requester = ?";
            paramsCount++;
        } 
        
        if (req.getRequestee() != null) {
            if (paramsCount > 0) {
                sql += " AND ";
            }
            sql += "requestee = ?";
        } 

        int i = 1;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (req.getRequester() != null) {
                stmt.setString(i, req.getRequester());
                i++;
            }

            if (req.getRequestee() != null) {
                stmt.setString(i, req.getRequestee());
                i++;
            }
            
            ResultSet result = stmt.executeQuery();
            SubRequest[] requests = new SubRequest[result.getFetchSize()];
            i = 0;
            while (result.next()) {
                requests[i] = new SubRequest(
                    result.getString("requester"),
                    result.getString("requestee"),
                    result.getString("requester_email"),
                    result.getTimestamp("created_at")
                );
                i++;
            }
            return requests;
        } catch (SQLException e) {
            System.out.println("Error finding request");
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }
}