CREATE DATABASE `board_service`;

CREATE TABLE `board_service`.`board_user` (
    `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `nickname` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE ,
    `password` VARCHAR(255) NOT NULL,
    `fail_count` TINYINT UNSIGNED DEFAULT 0,
    `status` ENUM('ACTIVE','BLOCKED','WITHDRAWN') DEFAULT 'ACTIVE',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `board_service`.`board` (
    `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(500) NOT NULL,
    `content` VARCHAR(2000) NOT NULL,
    `writer_id` INT UNSIGNED NOT NULL,
    `view_count` INT UNSIGNED DEFAULT 0,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_board_writer_id` FOREIGN KEY (`writer_id`) REFERENCES `board_user`(`id`)
);

INSERT INTO `board_service`.`board_user` (nickname, email, password, created_at, updated_at)
VALUES ('john','test@test.com','1234', NOW(),NOW()),
       ('wick','test2@test.com','1234', NOW(),NOW());

INSERT INTO `board_service`.`board` (title, content, writer_id, view_count, created_at, updated_at)
VALUES ('title_1','content_1' ,1, 0, NOW(),NOW()),
       ('title_2','content_2' ,2, 0, NOW(),NOW()),
       ('title_3','content_3' ,1, 0, NOW(),NOW()),
       ('title_4','content_4' ,2, 0, NOW(),NOW()),
       ('title_5','content_5' ,1, 0, NOW(),NOW()),
       ('title_6','content_6' ,2, 0, NOW(),NOW()),
       ('title_7','content_7' ,1, 0, NOW(),NOW()),
       ('title_8','content_8' ,2, 0, NOW(),NOW()),
       ('title_9','content_9' ,1, 0, NOW(),NOW()),
       ('title_10','content_10' ,2, 0, NOW(),NOW());