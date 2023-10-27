package com.soap.model;

import java.sql.Timestamp;

import com.soap.model.enums.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNotification {
    private int notificationId;  
    private User user;
    private String message;
    private Timestamp createdAt;
    private NotificationStatus status;  
}
