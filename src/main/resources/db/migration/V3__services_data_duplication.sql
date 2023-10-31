BEGIN;

-- This migration is made for synchronization purpose with PHP Service

CREATE TABLE IF NOT EXISTS `user` ( 
    `username` varchar(32) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS `book` (
    `book_id` integer PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS `author` (
    `author_id` integer PRIMARY KEY
);

ALTER TABLE `book_collection` 
    ADD FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `in_collection` 
    ADD FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `collection_subscription` 
    ADD FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `author_subscription` 
    ADD FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`)
    ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `author_subscription` 
    ADD FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_notification` 
    ADD FOREIGN KEY (`username`) REFERENCES `user` (`username`)
    ON DELETE CASCADE ON UPDATE CASCADE;;

COMMIT;