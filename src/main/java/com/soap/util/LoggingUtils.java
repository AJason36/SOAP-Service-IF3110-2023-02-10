package com.soap.util;

import com.soap.dao.LogDao;
import com.soap.exceptions.DaoException;
import com.soap.models.Log;

public class LoggingUtils {
    private static LogDao dao = new LogDao();

    public static void log(String description, String ipAddress, String endpoint) {
        Log log = new Log(description, ipAddress, endpoint);
        try {
            dao.createLog(log);
        } catch (DaoException e) {
            System.out.println(e.getCode() + ": " + e.getMessage());
        }
    }
}
