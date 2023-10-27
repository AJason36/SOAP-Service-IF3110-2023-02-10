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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private int notificationId;  

    @Column(name = "username")
    private String username;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;
}
