-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.17-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `account_status` tinyint(1) DEFAULT NULL,
  `account_type` varchar(20) DEFAULT NULL,
  `currency` varchar(20) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12933035 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (12933034,1,8770000,1,'Payment ','VND','emybank123','$2a$10$fpIoAmaNh4p0xWa7zc73OOkD1iwpuo7tPXQAObi5Fs7RWU67sFaEe','ROLE_USER','2022-03-04 06:02:16','2022-03-05 18:31:31');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Kien','Vu','Ha Noi','kientr@gmail.com','0983726314','Hoang Mai','2022-03-04 05:56:08','2022-03-05 14:07:32');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loans` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `loan_amount` bigint(20) DEFAULT NULL,
  `interest_rate` float DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `date_issued` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`loan_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,1,9500000,0.4,4,'2022-03-09','2022-03-04 14:05:17'),(2,1,21500000,0.2,5,'2022-03-09','2022-03-05 06:52:51'),(3,1,5500000,0.5,7,'2022-03-09','2022-03-05 09:56:02'),(4,1,7000000,0.2,4,'2022-03-09','2022-03-05 11:30:14'),(5,1,8500000,0.3,6,'2022-03-09','2022-03-05 11:30:45');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_account_id` int(11) DEFAULT NULL,
  `to_account_id` int(11) DEFAULT NULL,
  `date_issued` date DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`transaction_id`),
  KEY `from_account_id` (`from_account_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`from_account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,12933034,193233,'2022-03-04','TPbank',10000,'2022-03-04 15:14:29'),(2,12933034,2929111,'2022-03-04','Techcombank',10000,'2022-03-04 15:37:47'),(3,12933034,2932442,'2022-03-05','TPbank',10000,'2022-03-04 17:11:23'),(4,12933034,9202021,'2022-03-05','Techcombank',10000,'2022-03-04 17:12:15'),(5,12933034,29294334,'2022-03-05','VPbank',10000,'2022-03-04 17:16:47'),(6,12933034,993332,'2022-03-05','Sacombank',10000,'2022-03-04 17:18:19'),(7,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:11'),(8,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:13'),(9,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:13'),(10,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:13'),(11,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:14'),(12,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:14'),(13,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:14'),(14,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:14'),(15,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:14'),(16,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:15'),(17,12933034,9220112,'2022-03-05','Techcombank',50000,'2022-03-04 18:03:18'),(18,12933034,92232,'2022-03-05','Techcombank',50000,'2022-03-04 18:08:46'),(19,12933034,92232,'2022-03-05','Techcombank',50000,'2022-03-04 18:08:48'),(20,12933034,20321,'2022-03-05','Techcombank',50000,'2022-03-04 18:15:40'),(21,12933034,92242,'2022-03-05','Sacombank',50000,'2022-03-04 18:16:56'),(22,12933034,92291,'2022-03-05','TPbank',30000,'2022-03-04 18:17:36'),(23,12933034,201029,'2022-03-05','TPbank',60000,'2022-03-04 18:18:18'),(24,12933034,22421,'2022-03-05','Sacombank',50000,'2022-03-05 06:53:36'),(25,12933034,99222,'2022-03-05','VPbank',50000,'2022-03-05 06:58:59'),(26,12933034,93932,'2022-03-05','Techcombank',10000,'2022-03-05 07:06:31'),(27,12933034,929211,'2022-03-05','VPbank',50000,'2022-03-05 07:07:48'),(28,12933034,29291,'2022-03-05','Sacombank',20000,'2022-03-05 07:25:08'),(29,12933034,92921,'2022-03-05','Sacombank',30000,'2022-03-05 07:38:22'),(30,12933034,29292,'2022-03-05','VPbank',40000,'2022-03-05 07:41:22'),(31,12933034,911223,'2022-03-05','Sacombank',50000,'2022-03-05 07:44:21'),(32,12933034,292122,'2022-03-05','VPbank',60000,'2022-03-05 10:05:56'),(33,12933034,96553,'2022-03-05','Techcombank',50000,'2022-03-05 11:31:31');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bank'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-05 19:39:01
