BEGIN;

CREATE TABLE `sub_request` (
  `requester` varchar(32),
  `requestee` varchar(32),
  `requester_email` varchar(320) NOT NULL,
  `created_at` timestamp NOT NULL,
  PRIMARY KEY (`requester`, `requestee`)
);

CREATE TABLE `subscription` (
  `subscriber` varchar(32),
  `curator` varchar(32),
  `is_active` tinyint(1) NOT NULL,
  `approved_at` timestamp NOT NULL,
  `valid_until` timestamp,
  PRIMARY KEY (`subscriber`, `curator`)
);

COMMIT;
