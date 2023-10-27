ALTER TABLE `user_notification`
MODIFY COLUMN `status` enum('READ','UNREAD', 'ARCHIVED') NOT NULL;