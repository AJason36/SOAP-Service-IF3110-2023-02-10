package com.soap.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.soap.model.enums.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user_notification")
public class UserNotification {
    public UserNotification(User user, String message, Timestamp createdAt, NotificationStatus status) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
    }

    public UserNotification(String username, String message, Timestamp createdAt, NotificationStatus status) {
        this.user = new User(username);
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
    }

    public UserNotification(User user, String message, NotificationStatus status) {
        this(user, message, new Timestamp(System.currentTimeMillis()), status);
    }

    public UserNotification(String username, String message, NotificationStatus status) {
        this(username, message, new Timestamp(System.currentTimeMillis()), status);
    }
    
    public UserNotification(User user, String message) {
        this(user, message, NotificationStatus.UNREAD);
    }

    public UserNotification(String username, String message) {
        this(username, message, NotificationStatus.UNREAD);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private int notificationId;  

    private User user;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;
}
