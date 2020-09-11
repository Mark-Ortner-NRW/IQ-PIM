-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: innoqPim
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2020-09-10 07:57:34',1,'EXECUTED','8:f7fb536ddadd04a7245ffa0b4e54420b','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.8.7',NULL,NULL,'9724653804'),('20200909144300-1','jhipster','config/liquibase/changelog/20200909144300_added_entity_Brand.xml','2020-09-10 07:57:34',2,'EXECUTED','8:63c82449c14a4418b7460e996855bae2','createTable tableName=brand; dropDefaultValue columnName=created_at, tableName=brand; dropDefaultValue columnName=modified_at, tableName=brand','',NULL,'3.8.7',NULL,NULL,'9724653804'),('20200909144300-1-relations','jhipster','config/liquibase/changelog/20200909144300_added_entity_Brand.xml','2020-09-10 07:57:34',3,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'9724653804'),('20200909144300-1-data','jhipster','config/liquibase/changelog/20200909144300_added_entity_Brand.xml','2020-09-10 07:57:34',4,'EXECUTED','8:1e9add45bb334941db30a34c2a63b3ba','loadData tableName=brand','',NULL,'3.8.7','faker',NULL,'9724653804'),('20200909144400-1','jhipster','config/liquibase/changelog/20200909144400_added_entity_Product.xml','2020-09-10 07:57:34',5,'EXECUTED','8:061707e50a308d4b835ab1a8320371ac','createTable tableName=product; dropDefaultValue columnName=created_at, tableName=product; dropDefaultValue columnName=modified_at, tableName=product','',NULL,'3.8.7',NULL,NULL,'9724653804'),('20200909144400-1-relations','jhipster','config/liquibase/changelog/20200909144400_added_entity_Product.xml','2020-09-10 07:57:34',6,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.8.7',NULL,NULL,'9724653804'),('20200909144400-1-data','jhipster','config/liquibase/changelog/20200909144400_added_entity_Product.xml','2020-09-10 07:57:34',7,'EXECUTED','8:d1253bbe3d7c6c65abb89f519f45a6de','loadData tableName=product','',NULL,'3.8.7','faker',NULL,'9724653804'),('20200909144400-2','jhipster','config/liquibase/changelog/20200909144400_added_entity_constraints_Product.xml','2020-09-10 07:57:34',8,'EXECUTED','8:96ba8ed156dbc7a7270b4a2313628962','addForeignKeyConstraint baseTableName=product, constraintName=fk_product_brand_id, referencedTableName=brand','',NULL,'3.8.7',NULL,NULL,'9724653804');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` VALUES (1,_binary '','2020-09-10 16:27:46','2a02:908:1250:ad40:d072:f494:1386:7af3%en0 (2a02:908:1250:ad40:d072:f494:1386:7af3%en0)');
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `description` longtext,
  `is_active` bit(1) NOT NULL,
  `created_at` datetime,
  `modified_at` datetime,
  `created_by_id` varchar(24) DEFAULT NULL,
  `modified_by_id` varchar(24) DEFAULT NULL,
  `name_en_us` varchar(255) DEFAULT NULL,
  `description_en_us` longtext,
  `name_de_de` varchar(255) DEFAULT NULL,
  `description_de_de` longtext,
  `code` varchar(255) DEFAULT NULL,
  `owner_user_id` varchar(24) DEFAULT NULL,
  `assigned_user_id` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Proactive Home Loan Account parse',_binary '','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-08 23:57:31','2020-09-09 04:18:49','Internal interface','yellow white','copy','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Generic','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','communities','cyan','Baby Central Steel'),(2,'Personal Loan Account',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-08 13:53:23','2020-09-08 14:42:33','Soft redundant Shoes','Technician','RSS','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','card Licensed','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Unbranded','Expanded','magenta'),(3,'challenge Clothing',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '\0','2020-09-08 20:42:14','2020-09-08 15:45:59','pink Developer Michigan','User-centric','Auto Loan Account Soft Villages','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Fantastic California','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','auxiliary foreground','Gloves sky blue Persiste','Salad'),(4,'protocol Technician hardware',_binary '','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '\0','2020-09-09 09:23:49','2020-09-09 08:58:59','integrate','Swiss Franc Leone','Tennessee Awesome','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Tasty Rubber Ball','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Ergonomic Metal Shoes','Designer Configuration a','cyan Steel'),(5,'hacking',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '\0','2020-09-08 14:27:44','2020-09-08 16:01:02','Bedfordshire pixel Perso','Response Wooden Manager','local','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','deliverables Bike','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Keyboard Generic','compressing Brand applic','Computer'),(6,'Home Loan Account',_binary '','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-08 20:15:08','2020-09-09 11:16:20','Delaware lime','Keyboard Hill','info-mediaries generating full-range','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Center','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','middleware Tasty Fresh Soap','quantify blue Practical','Thailand Money Market Ac'),(7,'Fields',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '\0','2020-09-08 19:42:19','2020-09-09 08:01:26','Yemen experiences','Brazil','RAM','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','orange Croatia','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','success Towels infrastructure','copy California teal','Shoes zero administratio'),(8,'payment',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-09 07:30:54','2020-09-09 01:07:25','Universal','bottom-line','Views Plains','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Avon Sports Idaho','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','copying synthesizing','dynamic Saint Helena','engineer haptic'),(9,'Plastic Montana',_binary '\0','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-09 00:40:07','2020-09-09 00:22:03','relationships Consultant','multi-byte','overriding Associate North Dakota','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','web-enabled','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','array','Sri Lanka Rupee','multi-byte Credit Card A'),(10,'Concrete',_binary '','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',_binary '','2020-09-08 14:55:42','2020-09-08 13:05:02','adapter lime Senior','Agent calculating Sleek ','Credit Card Account Steel','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Forward','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','projection','Kina matrix','website bypassing paradi');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2020-09-10 07:59:53','AUTHENTICATION_SUCCESS'),(2,'admin','2020-09-10 08:00:06','AUTHENTICATION_SUCCESS'),(3,'admin','2020-09-10 08:00:44','AUTHENTICATION_SUCCESS'),(4,'admin','2020-09-10 12:31:53','AUTHENTICATION_SUCCESS'),(5,'admin','2020-09-10 12:31:53','AUTHENTICATION_SUCCESS'),(6,'admin','2020-09-10 13:17:02','AUTHENTICATION_SUCCESS'),(7,'admin','2020-09-10 14:47:01','AUTHENTICATION_SUCCESS'),(8,'admin','2020-09-10 15:56:13','AUTHENTICATION_SUCCESS'),(9,'admin','2020-09-10 15:56:14','AUTHENTICATION_SUCCESS'),(10,'admin','2020-09-10 16:02:08','AUTHENTICATION_SUCCESS'),(11,'admin','2020-09-11 03:35:59','AUTHENTICATION_SUCCESS'),(12,'admin','2020-09-11 04:26:21','AUTHENTICATION_SUCCESS'),(13,'admin','2020-09-11 04:45:41','AUTHENTICATION_SUCCESS'),(14,'admin','2020-09-11 04:47:22','AUTHENTICATION_SUCCESS'),(15,'admin','2020-09-11 06:38:42','AUTHENTICATION_SUCCESS'),(16,'admin','2020-09-11 06:49:42','AUTHENTICATION_SUCCESS'),(17,'admin','2020-09-11 06:53:32','AUTHENTICATION_SUCCESS'),(18,'admin','2020-09-11 06:55:01','AUTHENTICATION_SUCCESS'),(19,'admin','2020-09-11 06:55:45','AUTHENTICATION_SUCCESS'),(20,'admin','2020-09-11 06:57:04','AUTHENTICATION_SUCCESS'),(21,'admin','2020-09-11 07:29:51','AUTHENTICATION_SUCCESS'),(22,'admin','2020-09-11 07:55:39','AUTHENTICATION_SUCCESS'),(23,'admin','2020-09-11 07:56:42','AUTHENTICATION_SUCCESS'),(24,'admin','2020-09-11 08:16:20','AUTHENTICATION_SUCCESS'),(25,'admin','2020-09-11 08:36:33','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jhi_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','',_binary '\0','de',NULL,NULL,'system',NULL,NULL,'admin','2020-09-11 08:36:33'),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','',_binary '','de',NULL,NULL,'system',NULL,NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','',_binary '','de',NULL,NULL,'system',NULL,NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','',_binary '','de',NULL,NULL,'system',NULL,NULL,'system',NULL);
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `created_at` datetime,
  `modified_at` datetime,
  `sku` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `created_by_id` varchar(24) DEFAULT NULL,
  `modified_by_id` varchar(24) DEFAULT NULL,
  `product_family_id` varchar(24) DEFAULT NULL,
  `name_en_us` varchar(255) DEFAULT NULL,
  `name_de_de` varchar(255) DEFAULT NULL,
  `product_status` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `price_currency` varchar(255) DEFAULT NULL,
  `tax_id` varchar(24) DEFAULT NULL,
  `ean` varchar(255) DEFAULT NULL,
  `mpn` varchar(255) DEFAULT NULL,
  `packaging_id` varchar(24) DEFAULT NULL,
  `uvp` double DEFAULT NULL,
  `tag` longtext COMMENT 'default={[]}',
  `owner_user_id` varchar(24) DEFAULT NULL,
  `assigned_user_id` varchar(24) DEFAULT NULL,
  `final_price` double DEFAULT NULL,
  `final_price_currency` varchar(255) DEFAULT NULL,
  `long_description` longtext,
  `long_description_de_de` longtext,
  `long_description_en_us` longtext,
  `product_serie_id` varchar(24) DEFAULT NULL,
  `data` longtext,
  `catalog_id` varchar(24) DEFAULT NULL,
  `base_price_amount` double DEFAULT NULL,
  `packed_amount` double DEFAULT NULL,
  `image_id` varchar(24) DEFAULT NULL,
  `measuring_unit_id` varchar(24) DEFAULT NULL,
  `delivery_status` varchar(255) DEFAULT NULL,
  `delivery_status_de_de` varchar(255) DEFAULT NULL,
  `delivery_status_en_us` varchar(255) DEFAULT NULL,
  `supplied` longtext,
  `supplied_de_de` longtext,
  `supplied_en_us` longtext,
  `brand_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_brand_id` (`brand_id`),
  CONSTRAINT `fk_product_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Generic Steel Tuna',_binary '','2020-09-09 05:10:35','2020-09-08 13:08:18','quantify cross-platform support',_binary '','virtual wireless Computer',76146,'reboot deposit Sri Lanka','withdrawal Refined Metal','Steel','Kina networks','Metical partnerships','Infrastructure',46442,'transmitting intermediate withdrawal','green Credit Card Account','Gloves Lao People\'s Demo','neutral','Rustic Concrete Car','Computer Oklahoma Identi',20520,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Security','Data Identity',58781,'indigo Sports Human','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Personal Loan Account ye','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Cape Verde',40746,27546,'homogeneous withdrawal','invoice','Liaison','Keyboard','Central Ergonomic Concrete Ball','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(2,'pixel Gorgeous Rubber Cheese quantifying',_binary '\0','2020-09-08 17:47:06','2020-09-09 04:38:12','multi-byte online Forward',_binary '\0','Stand-alone Computer efficient',4074,'mobile Practical','User-friendly lavender w','sticky brand solution','Customer','Checking Account Gorgeous Fresh Ball','Unbranded Steel Computer',64809,'full-range','Consultant Chair','internet solution Operat','Chicken','Personal Loan Account Mobility multi-byte','clicks-and-mortar',62245,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','IB','Martinique Industrial Ru',25242,'Taka','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','National','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','bypassing e-business Sol',26258,54007,'Trinidad and Tobago Doll','Vista','Bedfordshire Product','invoice','open system Security','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(3,'Colombian Peso Unidad de Valor Real executive',_binary '\0','2020-09-08 17:46:14','2020-09-08 12:47:49','Sleek Gateway program',_binary '','Accounts convergence',59291,'Pula','copying','transmit','deposit global Sausages','value-added','Home Loan Account',42536,'Small sky blue turquoise','disintermediate generating Cheese','benchmark Berkshire','intuitive Chicken','turquoise','Rwanda Home Loan Account',67848,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Functionality markets Ta','Keyboard',32056,'Producer','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Buckinghamshire','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Specialist',6153,55191,'USB Unbranded Concrete H','Venezuela Human','deposit mint green time-frame','exuding Facilitator','Nebraska web services','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(4,'Future Regional transmitting',_binary '','2020-09-08 21:47:36','2020-09-09 06:55:07','Bacon payment efficient',_binary '','Mouse Serbia engage',40947,'Programmable Checking Ac','Functionality Plastic','Persistent primary','Unbranded Plastic Bacon','Palladium Malta','matrix Avon',92229,'Kansas clicks-and-mortar','protocol Intranet','Keys blue indexing','leverage Concrete PCI','Integration Producer Baby','teal open-source',99027,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','transform','XML',30263,'IB','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','payment payment','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Home Loan Account Chicke',17140,38194,'indigo','Paraguay','modular','neural back-end back-end','Infrastructure Consultant','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(5,'frictionless South Dakota Rwanda Franc',_binary '\0','2020-09-08 14:22:45','2020-09-08 16:01:41','Home Loan Account 1080p',_binary '\0','Manat Grocery Oman',83549,'deliverables','Pizza deposit','5th generation Money Mar','Division Rustic Steel Gloves open-source','leverage alarm back up','Pizza sticky Investment Account',93776,'San Marino distributed Licensed Plastic Sausages','orange Tools','Unbranded Music','Shoes','New Hampshire Saudi Arabia Berkshire','Games',29631,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','regional calculating','portals Bolivar Fuerte e',60886,'Borders port system','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','heuristic Global','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Viaduct metrics',88539,3511,'Utah','Auto Loan Account RAM','cutting-edge Movies','pixel','SMTP overriding primary','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(6,'Ergonomic Granite Table olive',_binary '\0','2020-09-09 08:34:09','2020-09-08 22:22:26','framework Ouguiya teal',_binary '','Central bleeding-edge SCSI',92572,'1080p','Kenyan Shilling 24/7','Universal Regional Incre','compressing','Ball','South Carolina Principal',58666,'Gorgeous yellow','black Designer','system','interface Optimization','Northern Mariana Islands Uzbekistan Sum','convergence',55120,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','backing up','Credit Card Account Idah',68314,'Designer THX Inlet','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Assistant Nevada','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Fish wireless Group',60773,31896,'Brooks Bike Unbranded','copy','Communications','Bacon primary Russian Federation','blue','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(7,'gold Maine Cotton',_binary '','2020-09-09 02:06:12','2020-09-09 00:57:27','Lights',_binary '','Views',83785,'Silver bleeding-edge','magenta','Personal Loan Account Au','Skyway Brand','Central','Advanced neutral',30207,'mobile Sleek Steel Bacon','Rustic Frozen Tuna','policy Berkshire Human','Soap Gourde US Dollar Fantastic','e-business Spur','XSS calculate',22879,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Lake','Fantastic Plastic Keyboa',606,'Personal Loan Account transmitting','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','utilize synthesizing','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Parks',16202,27703,'Saint Kitts and Nevis in','District','Rustic expedite','Baht','Licensed Fresh Bike','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(8,'Mobility Prairie',_binary '','2020-09-09 05:13:23','2020-09-09 07:41:31','Wells Expanded',_binary '\0','redundant framework',51814,'Inlet Cambridgeshire pol','Lek','Springs','connecting','Solomon Islands Square','Diverse Beauty Steel',97343,'synthesizing Inlet','Chips','Operative Montenegro Uga','Representative Alabama','index Malaysia','Optimization',94769,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Accounts HDD','Officer',39448,'Tonga quantifying','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Bike blue','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Baby Fantastic Metal Chi',28829,14191,'Common','Response','initiative solid state','Qatari Rial','interface reintermediate','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(9,'service-desk Shores',_binary '\0','2020-09-09 10:58:02','2020-09-09 00:34:36','generating Shoes invoice',_binary '','Quality Gorgeous hardware',71751,'AGP markets','deposit Louisiana Pitcai','turquoise','Alabama Rubber','virtual','real-time',64647,'quantify','Small Paradigm hard drive','Director','Investor Ouguiya','indexing Borders gold','cyan Berkshire',55641,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','reboot Multi-layered','quantifying Cotton',71242,'Bedfordshire cross-platform RSS','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','program Soap Ergonomic W','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Automotive',47240,83348,'multi-tasking','Secured','Persistent circuit','Clothing deposit XML','Specialist','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1),(10,'Home',_binary '','2020-09-09 03:51:18','2020-09-08 21:17:32','migration Shoes',_binary '','technologies HDD payment',53597,'New Hampshire Rustic Woo','metrics','Tennessee reintermediate','Palestinian Territory array','generate','Direct',78160,'Connecticut','Nevada','e-services Practical Met','forecast Gorgeous','Berkshire','invoice Fresh transmitti',1783,'JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Papua New Guinea Visiona','SQL Identity Enhanced',65821,'Junction Research','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','transmitter','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','Profound Strategist',35142,24673,'Table','compress','white bypass','Administrator Corners array','vortals','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.','JHipster is a development platform to generate, develop and deploy Spring Boot + Angular / React / Vue Web applications and Spring microservices.',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-11 13:53:37
