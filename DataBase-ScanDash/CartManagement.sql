CREATE DATABASE `cart` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `cart_items` (
  `sr_no` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(100) NOT NULL,
  `prod_name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `discount` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`sr_no`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
