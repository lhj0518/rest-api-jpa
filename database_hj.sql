CREATE SCHEMA `test12`;

CREATE TABLE `channel_contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `rs` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `creator` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `creator_contract` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creator_name` varchar(255) NOT NULL UNIQUE,
  `creator_rs` double NOT NULL,
  `channel_id` bigint DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CHANNEL` (`channel_id`),
  KEY `FK_CREATOR` (`creator_id`),
  CONSTRAINT `FK_CHANNEL` FOREIGN KEY (`channel_id`) REFERENCES `channel_contract` (`id`),
  CONSTRAINT `FK_CREATOR` FOREIGN KEY (`creator_id`) REFERENCES `creator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


CREATE TABLE `revenue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `revenue` double NOT NULL,
  `channel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_id_date_UNIQUE` (`channel_id`, `date`),
  KEY `FK_CHANNEL_REVENUE` (`channel_id`),
  CONSTRAINT `FK_CHANNEL_REVENUE` FOREIGN KEY (`channel_id`) REFERENCES `channel_contract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
