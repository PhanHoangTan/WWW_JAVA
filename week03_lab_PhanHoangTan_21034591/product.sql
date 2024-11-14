


-- Dumping database structure for productdb
DROP DATABASE IF EXISTS `productdb`;
CREATE DATABASE IF NOT EXISTS `productdb` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `productdb`;
-- Dumping structure for table productdb.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` text NOT NULL,
  `img_path` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping structure for table productdb.product_price
DROP TABLE IF EXISTS `product_price`;
CREATE TABLE IF NOT EXISTS `product_price` (
  `price_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `apply_date` datetime NOT NULL DEFAULT current_timestamp(),
  `value` double NOT NULL DEFAULT 0 COMMENT 'the price',
  `note` text DEFAULT NULL,
  PRIMARY KEY (`price_id`),
  CONSTRAINT `FK_product_price_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=LATIN1_SWEDISH_CI;



-- Thêm dữ liệu vào bảng product
INSERT INTO `product` (`name`, `description`, `img_path`) VALUES
('Product A', 'Description for Product A', '/images/product_a.jpg'),
('Product B', 'Description for Product B', '/images/product_b.jpg'),
('Product C', 'Description for Product C', '/images/product_c.jpg'),
('Product D', 'Description for Product D', '/images/product_d.jpg'),
('Product E', 'Description for Product E', '/images/product_e.jpg');

-- Thêm dữ liệu vào bảng product_price
INSERT INTO `product_price` (`product_id`, `apply_date`, `value`, `note`) VALUES
(1, '2024-01-01 10:00:00', 100.00, 'Initial price for Product A'),
(1, '2024-02-01 10:00:00', 110.00, 'Updated price for Product A'),
(2, '2024-01-15 10:00:00', 200.00, 'Initial price for Product B'),
(2, '2024-03-01 10:00:00', 210.00, 'Updated price for Product B'),
(3, '2024-01-20 10:00:00', 150.00, 'Initial price for Product C');

