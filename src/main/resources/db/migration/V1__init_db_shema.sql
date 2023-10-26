BEGIN;

DROP TABLE IF EXISTS `book_collection`;
DROP TABLE IF EXISTS `in_collection`;
DROP TABLE IF EXISTS `collection_subscription`;
DROP TABLE IF EXISTS `author_subscription`;
DROP TABLE IF EXISTS `user_notification`;

CREATE TABLE `book_collection` (
  `collection_id` int AUTO_INCREMENT PRIMARY KEY,
  `created_by` varchar(25) NOT NULL,
  `name` varchar(255) NOT NULL,
  `desc` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp,
  `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  `deleted_at` timestamp
);

CREATE TABLE `in_collection` (
  `collection_id` int,
  `book_id` int,
  PRIMARY KEY (`collection_id`, `book_id`)
);

CREATE TABLE `collection_subscription` (
  `collection_id` int,
  `username` varchar(25),
  `subscribed_at` timestamp NOT NULL,
  `is_unsubscribed` BOOLEAN NOT NULL DEFAULT FALSE,
  `unsubscribed_at` timestamp,
  PRIMARY KEY (`collection_id`, `username`)
);

CREATE TABLE `author_subscription` (
  `author_id` int,
  `username` varchar(25),
  `subscribed_at` timestamp NOT NULL,
  `is_unsubscribed` BOOLEAN NOT NULL DEFAULT FALSE,
  `unsubscribed_at` timestamp,
  PRIMARY KEY (`author_id`, `username`)
);

CREATE TABLE `user_notification` (
  `notification_id` int AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(25),
  `message` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `status` ENUM('unread', 'read', 'archived') NOT NULL
);

ALTER TABLE `in_collection` ADD FOREIGN KEY (`collection_id`) REFERENCES `book_collection` (`collection_id`);

ALTER TABLE `collection_subscription` ADD FOREIGN KEY (`collection_id`) REFERENCES `book_collection` (`collection_id`);

COMMIT;
```