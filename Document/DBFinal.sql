-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: house_decor
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(115) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(95) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Kitchen'),(2,'Bath'),(3,'Bedroom'),(4,'Living'),(5,'Dining'),(6,'Outdoor'),(7,'Other');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `city_code` varchar(15) NOT NULL,
  `cityName` varchar(105) DEFAULT NULL,
  PRIMARY KEY (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('1','Hồ Chí Minh'),('2','Hà Nội'),('3','Đà Nẵng');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idea_book`
--

DROP TABLE IF EXISTS `idea_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_book` (
  `idea_book_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(85) NOT NULL,
  `description` varchar(555) DEFAULT NULL,
  `is_public` bit(1) NOT NULL,
  `status` int(11) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idea_book_id`),
  KEY `fk_idea_book_user1_idx` (`user_id`),
  CONSTRAINT `fk_idea_book_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idea_book`
--

LOCK TABLES `idea_book` WRITE;
/*!40000 ALTER TABLE `idea_book` DISABLE KEYS */;
INSERT INTO `idea_book` VALUES (1,'Nhà Mới','Nhà được xây dựng vào năm 2015','',1,1),(2,'Giả Định','Test thử thôi','\0',0,1),(5,'Giả Định','aaaaaa','\0',0,1),(6,'thu 6v','b','',1,1),(7,'Nhà Bạn','Xây dựng sau khi kết hôn 10 năm','',1,2),(8,'Hùng','Hùng@gmail','\0',0,3),(9,'Hung 2','hung 2','\0',0,3),(10,'hung 3','hung 3','\0',0,3);
/*!40000 ALTER TABLE `idea_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idea_book_photo`
--

DROP TABLE IF EXISTS `idea_book_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_book_photo` (
  `photo_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(355) DEFAULT NULL,
  `tilte` varchar(145) DEFAULT NULL,
  `description` varchar(555) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `project_id` int(10) unsigned DEFAULT NULL,
  `idea_book_id` int(10) unsigned DEFAULT NULL,
  `category_id` int(10) unsigned NOT NULL,
  `style_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `fk_idea_book_photo_project1_idx` (`project_id`),
  KEY `fk_idea_book_photo_idea_book1_idx` (`idea_book_id`),
  KEY `fk_idea_book_photo_category1_idx` (`category_id`),
  KEY `fk_idea_book_photo_style1_idx` (`style_id`),
  CONSTRAINT `fk_idea_book_photo_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idea_book_photo_idea_book1` FOREIGN KEY (`idea_book_id`) REFERENCES `idea_book` (`idea_book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idea_book_photo_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idea_book_photo_style1` FOREIGN KEY (`style_id`) REFERENCES `style` (`style_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idea_book_photo`
--

LOCK TABLES `idea_book_photo` WRITE;
/*!40000 ALTER TABLE `idea_book_photo` DISABLE KEYS */;
INSERT INTO `idea_book_photo` VALUES (1,'https://st.hzcdn.com/fimgs/29d1a1b804d0a1c9_7772-w550-h734-b0-p0--modern-kitchen.jpg','Nhà đẹp','kỉ niệm',1,1,NULL,1,1),(2,'https://st.hzcdn.com/fimgs/da01a90704d0a26f_9796-w550-h734-b0-p0--traditional-exterior.jpg','Nhà đẹp','kỉ niệm',1,1,NULL,6,1),(4,'https://st.hzcdn.com/fimgs/88b104ff0886435b_0731-w550-h440-b0-p0--home-design.jpg','Winter 2016','Winter 2016',1,2,NULL,6,2),(5,'https://st.hzcdn.com/fimgs/f2d1d0cd0886436c_0735-w550-h734-b0-p0--home-design.jpg','Winter 2016','Winter 2016',1,2,NULL,2,2),(6,'https://st.hzcdn.com/fimgs/9851446c0886437e_0741-w550-h440-b0-p0--home-design.jpg','Winter 2016','Winter 2016',-1,2,NULL,4,2),(7,'https://st.hzcdn.com/fimgs/2a61185308864387_0745-w550-h440-b0-p0--home-design.jpg','Winter 2016','Winter 2016',1,2,NULL,1,2),(8,'https://st.hzcdn.com/fimgs/35513b3b08864398_0753-w550-h440-b0-p0--home-design.jpg','Winter 2016','Winter 2016',1,2,NULL,5,2),(9,'https://st.hzcdn.com/fimgs/bb51064c088643d3_9251-w550-h440-b0-p0--home-design.jpg','Winter 2016','Winter 2016',1,2,NULL,3,2),(10,'https://st.hzcdn.com/fimgs/5191b90f094ac958_5912-w550-h440-b0-p0--contemporary-exterior.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,6,3),(11,'https://st.hzcdn.com/fimgs/e51161a7094ac975_5927-w550-h440-b0-p0--contemporary-porch.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,6,3),(12,'https://st.hzcdn.com/fimgs/aff14e02094ac980_5932-w550-h734-b0-p0--contemporary-living-room.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,4,3),(13,'https://st.hzcdn.com/fimgs/20018bbf094ac98a_5932-w550-h734-b0-p0--contemporary.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,5,3),(14,'https://st.hzcdn.com/fimgs/6f21c3db094ac997_5932-w550-h440-b0-p0--transitional-kitchen.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',2,3,NULL,1,3),(15,'https://st.hzcdn.com/fimgs/7d0130b6094ac9a1_5922-w550-h734-b0-p0--asian-bedroom.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,3,3),(16,'https://st.hzcdn.com/fimgs/8451f547094ac9ac_2405-w550-h440-b0-p0--contemporary-bathroom.jpg','Sands Point House/Ole Sondresen Architect','Sands Point House/Ole Sondresen Architect',1,3,NULL,2,3),(17,'https://st.hzcdn.com/simgs/31c19d6e0ea1c99b_8-2390/bedroom.jpg','Spanish River Road Residence','Spanish River Road Residence',1,NULL,1,7,4),(18,'https://st.hzcdn.com/simgs/3a4181510ea1c979_8-7334/bedroom.jpg','Spanish River Road Residence','Spanish River Road Residence',1,NULL,1,7,4),(19,'images/199448_382897395112849_1092636300_n.jpg','á ','ú',1,NULL,5,7,4),(33,'https://st.hzcdn.com/fimgs/6f21c3db094ac997_5932-w550-h440-b0-p0--transitional-kitchen.jpg','33','33',1,NULL,6,7,4),(34,'https://st.hzcdn.com/fimgs/29d1a1b804d0a1c9_7772-w550-h734-b0-p0--modern-kitchen.jpg','34','34',1,NULL,6,7,4),(40,'images/1500274457577577407_723402844357058_597095575_n.jpg','aaa','test',2,NULL,2,7,4),(41,'images/1500274472113577407_723402844357058_597095575_n.jpg','aaaabbb','cccc',1,NULL,2,7,4),(42,'images/1500274484824577407_723402844357058_597095575_n.jpg','mmmm','bbbbas',1,NULL,2,7,4),(43,'images/1500274593410577407_723402844357058_597095575_n.jpg',' vád','xác',2,NULL,2,7,4),(44,'images/1500275014766600_e097f685-310f-4c14-b1e7-ab25bfac13fb9.jpg','thu 1','ha',1,NULL,2,7,4),(45,'images/1500282913479set-do-doi-thuyen-tinh-cap-ben-1395980444.jpg','test mới','mới',1,NULL,2,7,4),(46,'images/1500283364251124135846043_yume_photo.jpg','uhm','bbb',2,NULL,2,7,4),(47,'https://st.hzcdn.com/fimgs/ca419e49064b90de_0208-w550-h440-b0-p0--transitional-living-room.jpg','Phòng khách','tao nhã',0,NULL,7,7,4),(48,'https://st.hzcdn.com/fimgs/22c13955063cd866_3550-w550-h734-b0-p0--contemporary-living-room.jpg','Ghê sopha','Mới',1,NULL,7,7,4);
/*!40000 ALTER TABLE `idea_book_photo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `house_decor`.`CoppyOnTableIdeaBookPhoto`
AFTER UPDATE ON `house_decor`.`idea_book_photo`
FOR EACH ROW
BEGIN
		IF(NEW.status = 1 AND NEW.project_id IS NULL) THEN
		INSERT INTO idea_book_photo_ref  VALUES (OLD.idea_book_id, OLD.photo_id ,OLD.description);
		END IF;
        IF(NEW.status = -1 AND OLD.status = 1) THEN
		DELETE FROM idea_book_photo_ref WHERE photo_id=NEW.photo_id;

		END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `idea_book_photo_ref`
--

DROP TABLE IF EXISTS `idea_book_photo_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idea_book_photo_ref` (
  `idea_book_id` int(10) unsigned NOT NULL,
  `photo_id` int(10) unsigned NOT NULL,
  `comment` varchar(555) DEFAULT NULL,
  PRIMARY KEY (`idea_book_id`,`photo_id`),
  KEY `fk_photo_ref_idx` (`photo_id`),
  CONSTRAINT `fk_idea_book_ref` FOREIGN KEY (`idea_book_id`) REFERENCES `idea_book` (`idea_book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_photo_ref` FOREIGN KEY (`photo_id`) REFERENCES `idea_book_photo` (`photo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idea_book_photo_ref`
--

LOCK TABLES `idea_book_photo_ref` WRITE;
/*!40000 ALTER TABLE `idea_book_photo_ref` DISABLE KEYS */;
INSERT INTO `idea_book_photo_ref` VALUES (1,2,'aaaa'),(1,7,''),(1,17,'à uhm'),(1,18,'ahihi'),(1,48,''),(2,16,''),(2,45,'mới'),(5,16,'test'),(5,18,'b'),(5,19,'ú'),(6,16,'test'),(6,33,'33'),(6,34,'34'),(6,48,'test add photo to ideabook'),(7,48,'Mới'),(9,48,'test');
/*!40000 ALTER TABLE `idea_book_photo_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` varchar(20) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `price` float NOT NULL,
  `order_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `fk_order_detail_order_idx` (`order_id`),
  KEY `fk_order_detail_product` (`product_id`),
  CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created_time` datetime NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` varchar(20) NOT NULL,
  `product_name` varchar(145) DEFAULT NULL,
  `bar_code` varchar(45) DEFAULT NULL,
  `descripsion` varchar(545) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `material` varchar(45) DEFAULT NULL,
  `warranty` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `style_style_id` int(10) unsigned NOT NULL,
  `category_category_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_style1_idx` (`style_style_id`),
  KEY `fk_product_category1_idx` (`category_category_id`),
  KEY `fk_product_seller_info1_idx` (`user_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_seller_info1` FOREIGN KEY (`user_id`) REFERENCES `seller_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_style1` FOREIGN KEY (`style_style_id`) REFERENCES `style` (`style_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','Chantal Stools, Set of 2, Ivory, Counter Height',NULL,'These comfortably soft, ivory bonded leather counter stools are a perfect transitional piece from your kitchen to your living room. Studded accents are featured along the edges of the seat and the black metal kickplate gives this stool a contemporary look.',128000,15,'L','Gỗ','10 tháng',1,1,1,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_photo`
--

DROP TABLE IF EXISTS `product_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_photo` (
  `photo_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(245) DEFAULT NULL,
  `title` varchar(115) DEFAULT NULL,
  `description` varchar(545) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `product_id` varchar(20) NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`photo_id`),
  KEY `fk_product_photo_product1_idx` (`product_id`),
  CONSTRAINT `fk_product_photo_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_photo`
--

LOCK TABLES `product_photo` WRITE;
/*!40000 ALTER TABLE `product_photo` DISABLE KEYS */;
INSERT INTO `product_photo` VALUES (5,'https://st.hzcdn.com/simgs/b70140280339a569_8-3061/transitional-bar-stools-and-counter-stools.jpg','Jaeden Stools','The Jaeden stools combine elegance and structure. It features a well-padded seat, natural colored legs and nailhead accent curved along the seat. This stool is a perfect transitional piece for your kitchen to your living room.',1,'1','2017-07-23 08:07:36');
/*!40000 ALTER TABLE `product_photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professional`
--

DROP TABLE IF EXISTS `professional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professional` (
  `professional_name` varchar(45) DEFAULT NULL,
  `address` varchar(245) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `type_of_work_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_professional_user2_idx` (`user_id`),
  KEY `fk_professional_type_of_work2_idx` (`type_of_work_id`),
  CONSTRAINT `fk_professional_type_of_work2` FOREIGN KEY (`type_of_work_id`) REFERENCES `type_of_work` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_professional_user2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professional`
--

LOCK TABLES `professional` WRITE;
/*!40000 ALTER TABLE `professional` DISABLE KEYS */;
INSERT INTO `professional` VALUES ('Sơn Ngọc','HCM',1,1),('Mai Company','HN',2,1),('Mạnh Hùng product','ĐN',3,1);
/*!40000 ALTER TABLE `professional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `project_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(155) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cost` float unsigned DEFAULT NULL,
  `website` varchar(145) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `keywords` varchar(145) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `professional_user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `fk_project_professional1_idx` (`professional_user_id`),
  CONSTRAINT `fk_project_professional1` FOREIGN KEY (`professional_user_id`) REFERENCES `professional` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Nhà đẹp','Quận Tân Bình- HCM',NULL,NULL,2007,NULL,1,1),(2,'Winter 2016','Quận Phú Nhuận - HCM',NULL,NULL,2001,NULL,1,2),(3,'Sands Point House/Ole Sondresen Architect','Japan',NULL,NULL,2010,NULL,1,3);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(145) DEFAULT NULL,
  `description` varchar(545) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `seller_info_user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_promotion_seller_info1_idx` (`seller_info_user_id`),
  CONSTRAINT `fk_promotion_seller_info1` FOREIGN KEY (`seller_info_user_id`) REFERENCES `seller_info` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_detail`
--

DROP TABLE IF EXISTS `promotion_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_detail` (
  `promotion_detail_id` int(10) unsigned NOT NULL,
  `promotion_id` int(10) unsigned NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `discount` float NOT NULL,
  PRIMARY KEY (`promotion_detail_id`),
  KEY `fk_promotion_detail_promotion_idx` (`promotion_id`),
  KEY `fk_promotion_detail_product_idx` (`product_id`),
  CONSTRAINT `fk_promotion_detail_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_promotion_detail_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_detail`
--

LOCK TABLES `promotion_detail` WRITE;
/*!40000 ALTER TABLE `promotion_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Member'),(2,'Professional'),(3,'Seller');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_info`
--

DROP TABLE IF EXISTS `seller_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_info` (
  `tax_number` varchar(45) DEFAULT NULL,
  `store_address` varchar(245) DEFAULT NULL,
  `seller_name` varchar(245) DEFAULT NULL,
  `start_date` date NOT NULL,
  `due_date` date NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_seller_info_professional1_idx1` (`user_id`),
  CONSTRAINT `fk_seller_info_professional1` FOREIGN KEY (`user_id`) REFERENCES `professional` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_info`
--

LOCK TABLES `seller_info` WRITE;
/*!40000 ALTER TABLE `seller_info` DISABLE KEYS */;
INSERT INTO `seller_info` VALUES ('6400372733','12 Nguyễn Văn Quá','Ngọc','2006-11-10','2017-11-10','01277892992',1),('6400372731','14 Song Hành','Mai','2006-11-12','2017-11-12','0989567432',2);
/*!40000 ALTER TABLE `seller_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

DROP TABLE IF EXISTS `style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `style` (
  `style_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (1,'Modern'),(2,'Victorian'),(3,'Asian'),(4,'Other');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking`
--

DROP TABLE IF EXISTS `tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracking` (
  `count` int(11) NOT NULL,
  `last_update` datetime NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`category_id`),
  KEY `fk_tracking_user1_idx` (`user_id`),
  KEY `fk_tracking_category1_idx` (`category_id`),
  CONSTRAINT `fk_tracking_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tracking_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking`
--

LOCK TABLES `tracking` WRITE;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_work`
--

DROP TABLE IF EXISTS `type_of_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_of_work` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(85) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_work`
--

LOCK TABLES `type_of_work` WRITE;
/*!40000 ALTER TABLE `type_of_work` DISABLE KEYS */;
INSERT INTO `type_of_work` VALUES (1,'Architects Designers'),(2,'Home Builders'),(3,'Interior Designers'),(4,'Landscape Designers'),(5,'Other Services');
/*!40000 ALTER TABLE `type_of_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(75) NOT NULL,
  `password` varchar(55) NOT NULL,
  `firstname` varchar(40) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `about_me` mediumtext,
  `status` int(11) DEFAULT NULL,
  `primary_address` varchar(255) DEFAULT NULL,
  `sencond_address` varchar(255) DEFAULT NULL,
  `city_code` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_city1_idx` (`city_code`),
  KEY `fk_user_role1` (`role_id`),
  CONSTRAINT `fk_user_city1` FOREIGN KEY (`city_code`) REFERENCES `city` (`city_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'k3ttrum@gmail.com','123','Cao Đức','Sơn Ngọc','1992-11-10','2006-02-04','01277892992',2,1,'no thing',1,'1 Phan Chu Trinh',NULL,'1'),(2,'mai@gmail.com','123','Nguyễn Thị','Mai','1993-03-06','2006-03-06','0987641648',2,2,'uhmj',1,'2 Nguyễn Văn Quá',NULL,'2'),(3,'hung@gmail.com','123','Nguyễn Mạnh','Hùng','1989-12-12','2006-04-04','01645789645',2,1,'aaaaaaaaaa',1,'3 Liên Chiểu',NULL,'3'),(4,'thao@gmail.com','123','Cao Thu','Thảo','1990-05-08','2007-01-01','091941657',1,2,'bbbbbbbbbb',1,'198 Trường Chinh',NULL,'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-15  8:54:40
