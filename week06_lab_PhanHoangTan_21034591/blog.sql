-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.2.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for blog
DROP DATABASE IF EXISTS `blog`;
CREATE DATABASE IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `blog`;

-- Dumping structure for table blog.post
DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_Id` bigint(20) NOT NULL,
  `parent_Id` bigint(20) DEFAULT NULL,
  `title` varchar(75) NOT NULL,
  `meta_Title` varchar(100) DEFAULT NULL,
  `summary` tinytext DEFAULT NULL,
  `published` tinyint(1) NOT NULL DEFAULT 0,
  `created_At` datetime NOT NULL,
  `updated_At` datetime DEFAULT NULL,
  `published_At` datetime DEFAULT NULL,
  `content` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_post_user` (`author_Id`),
  KEY `idx_post_parent` (`parent_Id`),
  CONSTRAINT `fk_post_parent` FOREIGN KEY (`parent_Id`) REFERENCES `post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user` FOREIGN KEY (`author_Id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table blog.post_comment
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE IF NOT EXISTS `post_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_Id` bigint(20) NOT NULL,
  `parent_Id` bigint(20) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `published` tinyint(1) NOT NULL DEFAULT 0,
  `created_At` datetime NOT NULL,
  `published_At` datetime DEFAULT NULL,
  `content` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_comment_post` (`post_Id`),
  KEY `idx_comment_parent` (`parent_Id`),
  CONSTRAINT `fk_comment_parent` FOREIGN KEY (`parent_Id`) REFERENCES `post_comment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post` FOREIGN KEY (`post_Id`) REFERENCES `post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

-- Dumping structure for table blog.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(50) DEFAULT NULL,
  `middle_Name` varchar(50) DEFAULT NULL,
  `last_Name` varchar(50) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password_Hash` varchar(32) NOT NULL,
  `registered_At` datetime NOT NULL,
  `last_Login` datetime DEFAULT NULL,
  `intro` tinytext DEFAULT NULL,
  `profile` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_mobile` (`mobile`),
  UNIQUE KEY `uq_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
INSERT INTO user (first_Name, middle_Name, last_Name, mobile, email, password_Hash, registered_At, last_Login, intro, profile)
VALUES
('John', 'A', 'Doe', '1234567890', 'john.doe@example.com', 'hash1', '2023-01-01T00:00:00Z', '2023-01-10T00:00:00Z', 'Intro 1', 'Profile 1'),
('Jane', 'B', 'Smith', '1234567891', 'jane.smith@example.com', 'hash2', '2023-01-02T00:00:00Z', '2023-01-11T00:00:00Z', 'Intro 2', 'Profile 2'),
('Alice', 'C', 'Johnson', '1234567892', 'alice.johnson@example.com', 'hash3', '2023-01-03T00:00:00Z', '2023-01-12T00:00:00Z', 'Intro 3', 'Profile 3'),
('Bob', 'D', 'Brown', '1234567893', 'bob.brown@example.com', 'hash4', '2023-01-04T00:00:00Z', '2023-01-13T00:00:00Z', 'Intro 4', 'Profile 4'),
('Charlie', 'E', 'Davis', '1234567894', 'charlie.davis@example.com', 'hash5', '2023-01-05T00:00:00Z', '2023-01-14T00:00:00Z', 'Intro 5', 'Profile 5'),
('David', 'F', 'Miller', '1234567895', 'david.miller@example.com', 'hash6', '2023-01-06T00:00:00Z', '2023-01-15T00:00:00Z', 'Intro 6', 'Profile 6'),
('Eve', 'G', 'Wilson', '1234567896', 'eve.wilson@example.com', 'hash7', '2023-01-07T00:00:00Z', '2023-01-16T00:00:00Z', 'Intro 7', 'Profile 7'),
('Frank', 'H', 'Moore', '1234567897', 'frank.moore@example.com', 'hash8', '2023-01-08T00:00:00Z', '2023-01-17T00:00:00Z', 'Intro 8', 'Profile 8'),
('Grace', 'I', 'Taylor', '1234567898', 'grace.taylor@example.com', 'hash9', '2023-01-09T00:00:00Z', '2023-01-18T00:00:00Z', 'Intro 9', 'Profile 9'),
('Hank', 'J', 'Anderson', '1234567899', 'hank.anderson@example.com', 'hash10', '2023-01-10T00:00:00Z', '2023-01-19T00:00:00Z', 'Intro 10', 'Profile 10');
INSERT INTO post (author_Id, parent_Id, title, meta_Title, summary, published, created_At, updated_At, published_At, content)
VALUES
(1, NULL, 'Post Title 1', 'Meta Title 1', 'Summary 1', TRUE, '2023-01-01T00:00:00Z', '2023-01-02T00:00:00Z', '2023-01-03T00:00:00Z', 'Content 1'),
(2, NULL, 'Post Title 2', 'Meta Title 2', 'Summary 2', TRUE, '2023-01-02T00:00:00Z', '2023-01-03T00:00:00Z', '2023-01-04T00:00:00Z', 'Content 2'),
(3, NULL, 'Post Title 3', 'Meta Title 3', 'Summary 3', TRUE, '2023-01-03T00:00:00Z', '2023-01-04T00:00:00Z', '2023-01-05T00:00:00Z', 'Content 3'),
(4, NULL, 'Post Title 4', 'Meta Title 4', 'Summary 4', TRUE, '2023-01-04T00:00:00Z', '2023-01-05T00:00:00Z', '2023-01-06T00:00:00Z', 'Content 4'),
(5, NULL, 'Post Title 5', 'Meta Title 5', 'Summary 5', TRUE, '2023-01-05T00:00:00Z', '2023-01-06T00:00:00Z', '2023-01-07T00:00:00Z', 'Content 5'),
(6, NULL, 'Post Title 6', 'Meta Title 6', 'Summary 6', TRUE, '2023-01-06T00:00:00Z', '2023-01-07T00:00:00Z', '2023-01-08T00:00:00Z', 'Content 6'),
(7, NULL, 'Post Title 7', 'Meta Title 7', 'Summary 7', TRUE, '2023-01-07T00:00:00Z', '2023-01-08T00:00:00Z', '2023-01-09T00:00:00Z', 'Content 7'),
(8, NULL, 'Post Title 8', 'Meta Title 8', 'Summary 8', TRUE, '2023-01-08T00:00:00Z', '2023-01-09T00:00:00Z', '2023-01-10T00:00:00Z', 'Content 8'),
(9, NULL, 'Post Title 9', 'Meta Title 9', 'Summary 9', TRUE, '2023-01-09T00:00:00Z', '2023-01-10T00:00:00Z', '2023-01-11T00:00:00Z', 'Content 9'),
(10, NULL, 'Post Title 10', 'Meta Title 10', 'Summary 10', TRUE, '2023-01-10T00:00:00Z', '2023-01-11T00:00:00Z', '2023-01-12T00:00:00Z', 'Content 10');
INSERT INTO post_comment (post_Id, parent_Id, title, published, created_At, published_At, content)
VALUES
(1, NULL, 'Comment Title 1', TRUE, '2023-01-01T00:00:00Z', '2023-01-02T00:00:00Z', 'Content 1'),
(2, NULL, 'Comment Title 2', TRUE, '2023-01-02T00:00:00Z', '2023-01-03T00:00:00Z', 'Content 2'),
(3, NULL, 'Comment Title 3', TRUE, '2023-01-03T00:00:00Z', '2023-01-04T00:00:00Z', 'Content 3'),
(4, NULL, 'Comment Title 4', TRUE, '2023-01-04T00:00:00Z', '2023-01-05T00:00:00Z', 'Content 4'),
(5, NULL, 'Comment Title 5', TRUE, '2023-01-05T00:00:00Z', '2023-01-06T00:00:00Z', 'Content 5'),
(6, NULL, 'Comment Title 6', TRUE, '2023-01-06T00:00:00Z', '2023-01-07T00:00:00Z', 'Content 6'),
(7, NULL, 'Comment Title 7', TRUE, '2023-01-07T00:00:00Z', '2023-01-08T00:00:00Z', 'Content 7'),
(8, NULL, 'Comment Title 8', TRUE, '2023-01-08T00:00:00Z', '2023-01-09T00:00:00Z', 'Content 8'),
(9, NULL, 'Comment Title 9', TRUE, '2023-01-09T00:00:00Z', '2023-01-10T00:00:00Z', 'Content 9'),
(10, NULL, 'Comment Title 10', TRUE, '2023-01-10T00:00:00Z', '2023-01-11T00:00:00Z', 'Content 10');