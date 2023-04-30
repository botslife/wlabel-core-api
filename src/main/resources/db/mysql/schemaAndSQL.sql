CREATE DATABASE  IF NOT EXISTS `core` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `core`;
-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: core
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `centre`
--

DROP TABLE IF EXISTS `centre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centre` (
  `id` int NOT NULL,
  `chairman_teacher_id` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_image` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `thumb_image` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `city_id` int NOT NULL,
  `site_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FKn06twke55y9mnx613174vs9ed` (`city_id`),
  KEY `FKwdcrdv6fpyrwvm1gnu3k76tt` (`site_id`),
  CONSTRAINT `FKn06twke55y9mnx613174vs9ed` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKwdcrdv6fpyrwvm1gnu3k76tt` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centre`
--

LOCK TABLES `centre` WRITE;
/*!40000 ALTER TABLE `centre` DISABLE KEYS */;
INSERT INTO `centre` VALUES (9,1,'Aylesbury TM centre ','raja@tm.org','fullimage.url','00447508472911','Aylesbury TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/aylesbury',1,3),(15,1,'Singapore Councourse TM centre - the only TM centre in Singapore ','raja@tm.org','fullimage.url','00447508472911','Singapore TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/singapore',6,13),(19,1,'Visakhapatnam IT hills TM centre - Medidate right on the beach ','raja@tm.org','fullimage.url','00447508472911',' Vizag TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/singapore',4,14);
/*!40000 ALTER TABLE `centre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centre_settings`
--

DROP TABLE IF EXISTS `centre_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centre_settings` (
  `id` int NOT NULL,
  `centre_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9b2g4xuesai4hdwupxrso0wb6` (`centre_id`),
  CONSTRAINT `FK9b2g4xuesai4hdwupxrso0wb6` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centre_settings`
--

LOCK TABLES `centre_settings` WRITE;
/*!40000 ALTER TABLE `centre_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `centre_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `state_full_name` varchar(255) DEFAULT NULL,
  `state_short_code` varchar(255) DEFAULT NULL,
  `country_iso3` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qsstlki7ni5ovaariyy9u8y79` (`name`),
  KEY `FK3ct1vjyavjc2uf0xjufsl8v28` (`country_iso3`),
  CONSTRAINT `FK3ct1vjyavjc2uf0xjufsl8v28` FOREIGN KEY (`country_iso3`) REFERENCES `country` (`iso3`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,27.47,77.68,'Aylesbury','HP219XZ','Bucks','BC','GBR'),(4,27.47,77.68,'Visakhapatnam','530001','Andhra Pradesh','AP','IND'),(6,27.47,77.68,'Singapore','NOCODE','Singapore','SG','SGP');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `iso3` varchar(255) NOT NULL,
  `iso2` varchar(255) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`iso3`),
  UNIQUE KEY `UK_h9ojhx8niamk5q3cpa1e1hvou` (`iso2`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('GBR','GB',53.3,12.2,'Great Britian'),('IND','IN',53.3,12.2,'India'),('SGP','SG',53.3,12.2,'Singapore');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site` (
  `id` int NOT NULL,
  `domain` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `country_iso3` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qsgk5cjl6wt1xvhdeqymoymqb` (`domain`),
  UNIQUE KEY `UK_f9iil6uu8d9ohutpr2irlpvio` (`name`),
  KEY `FKgdtllv3isrtb3hb9rtrhai948` (`country_iso3`),
  CONSTRAINT `FKgdtllv3isrtb3hb9rtrhai948` FOREIGN KEY (`country_iso3`) REFERENCES `country` (`iso3`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (3,'uk.tm.org','UK site','ACTIVE','GBR'),(13,'sgp.tm.org','Singapore site','ACTIVE','SGP'),(14,'in.tm.org','India site','ACTIVE','IND');
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_centre`
--

DROP TABLE IF EXISTS `site_centre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_centre` (
  `id` int NOT NULL,
  `centre_id` int NOT NULL,
  `site_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4vlk1qlriris8wrri78q9x6pn` (`centre_id`),
  KEY `FKdf1ihu7bqbncy7c4fguhuwd3y` (`site_id`),
  CONSTRAINT `FK4vlk1qlriris8wrri78q9x6pn` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`id`),
  CONSTRAINT `FKdf1ihu7bqbncy7c4fguhuwd3y` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_centre`
--

LOCK TABLES `site_centre` WRITE;
/*!40000 ALTER TABLE `site_centre` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_centre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_settings`
--

DROP TABLE IF EXISTS `site_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site_settings` (
  `id` int NOT NULL,
  `site_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl8r9xkih3i7xspcupxa6inoa6` (`site_id`),
  CONSTRAINT `FKl8r9xkih3i7xspcupxa6inoa6` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_settings`
--

LOCK TABLES `site_settings` WRITE;
/*!40000 ALTER TABLE `site_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue` (
  `id` int NOT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address_notes` varchar(255) DEFAULT NULL,
  `brief_address` varchar(255) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `total_places` int NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9uyuoc3vs9lv1qpae6j8q40xe` (`name`),
  KEY `FKjvb2os94uuou6bjriy78c8k7i` (`city_id`),
  CONSTRAINT `FKjvb2os94uuou6bjriy78c8k7i` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (2,'92','Dalesford Road','Almost at the end of the road','Aylesbury TM Centre',28.52,83.59,'Aylesbury','HP219XZ',1000,1),(5,'1009','RTC Depot','Close to Supermarket','Simhachalam TM centre',28.52,83.59,'Simhachalam TM','530053',1089,4),(7,'The Councourse','Floor 12, Flat 2034','Opposite to food restaurent','GR Councourse',28.52,83.59,'Singapore Concourse','CONCOURSE',9,6);
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 10:50:58
