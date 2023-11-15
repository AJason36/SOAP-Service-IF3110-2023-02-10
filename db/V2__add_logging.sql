BEGIN;

CREATE TABLE `request_log` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    ip_address VARCHAR(45),
    endpoint VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMIT;
