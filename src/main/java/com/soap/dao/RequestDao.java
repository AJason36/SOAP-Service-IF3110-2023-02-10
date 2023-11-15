package com.soap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.soap.exceptions.DaoException;
import com.soap.models.ResponseCode;
import com.soap.models.SubRequest;
import com.soap.models.builders.SubRequestBuilder;
import com.soap.util.DbUtils;

public class RequestDao {

    private Connection conn = DbUtils.getConnection();
    private SubRequestBuilder reqBuilder = new SubRequestBuilder();

    /**
     * Create a request to database
     * @param req 
     * @throws DaoException
     */
    public void createRequest(SubRequest req) throws DaoException {
        String sql = "INSERT INTO sub_request (requester, requestee, requester_email, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, req.getRequester());
            stmt.setString(2, req.getRequestee());
            stmt.setString(3, req.getRequesterEmail());
            
            stmt.setTimestamp(4, DbUtils.gregorianXMLToTimestamp(req.getCreatedAt()));
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
        String sql = "DELETE FROM sub_request WHERE requester = ? AND requestee = ?";
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
        String sql = "SELECT * FROM sub_request WHERE ";
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
            List<SubRequest> requests = new ArrayList<SubRequest>();
            while (result.next()) {
                SubRequest request = reqBuilder
                    .setRequester(result.getString("requester"))
                    .setRequestee(result.getString("requestee"))
                    .setRequesterEmail(result.getString("requester_email"))
                    .setCreatedAt(result.getTimestamp("created_at"))
                    .create();
                requests.add(request);
            }
            
            return requests.toArray(new SubRequest[requests.size()]);
        } catch (SQLException e) {
            System.out.println("Error finding request");
            throw new DaoException(e.getMessage(), ResponseCode.SERVER_ERROR);
        }
    }
}