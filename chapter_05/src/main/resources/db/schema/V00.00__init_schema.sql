CREATE DATABASE `board_service`;

CREATE USER `board_account`@'%' IDENTIFIED BY 'board_account';

GRANT ALL PRIVILEGES ON `board_service`.* TO `board_account`@'%';
-- 🔼 database init

-- 🔽 database use
USE `board_service`;

CREATE TABLE `board_user` (
    `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'PK',
    `email` VARCHAR(300) NOT NULL UNIQUE ,
    `password` VARCHAR(100) NOT NULL,
    `nickname` VARCHAR(255) NOT NULL,
    `login_trial` TINYINT UNSIGNED NOT NULL DEFAULT 0,
    `activate` BOOLEAN NOT NULL DEFAULT TRUE,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NULL
) COMMENT 'user table';

CREATE TABLE `board` (
    `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'PK',
    `title` VARCHAR(200) NOT NULL COMMENT 'board title',
    `content` VARCHAR(3000) NOT NULL COMMENT 'content',
    `writer_id` INT UNSIGNED NOT NULL,
    `view_count` INT UNSIGNED NOT NULL DEFAULT 0,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NULL,
    CONSTRAINT `fk_board_writer_id` FOREIGN KEY (`writer_id`) REFERENCES `board_user`(`id`)
) COMMENT 'board table';