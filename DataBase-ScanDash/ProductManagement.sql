CREATE DATABASE `products` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `products` (
  `sr_no` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sr_no`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `grocery_items` (
  `sr_no` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  `manufacture_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sr_no`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `fresh_produce` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  `manufacture_date` varchar(20) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `organic_or_inorganic` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `packed_grocery_items` (
  `sr_no` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  `manufacture_date` varchar(20) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sr_no`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `fresh_grocery` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  `manufacture_date` varchar(20) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `electronics` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `warranty_period` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `cosmetics` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `ingredients` text,
  `brand` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bakery_items` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `expiry_date` varchar(20) DEFAULT NULL,
  `manufacture_date` varchar(20) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `gluten_info` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `appliances` (
  `sr_number` int NOT NULL AUTO_INCREMENT,
  `prod_id` varchar(20) DEFAULT NULL,
  `capacity` decimal(10,2) DEFAULT NULL,
  `efficiency_rate` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`sr_number`),
  UNIQUE KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
