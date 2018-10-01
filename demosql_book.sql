-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: demosql
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  ` BOOK_ID` int(10) NOT NULL,
  ` SORT` varchar(10) DEFAULT NULL,
  ` BOOK_NAME` varchar(50) DEFAULT NULL,
  ` WRITER` varchar(10) DEFAULT NULL,
  ` OUTPUT` varchar(50) DEFAULT NULL,
  ` PRICE` int(3) DEFAULT NULL,
  PRIMARY KEY (` BOOK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (112266,'TP3/12','FoxBASE','张三','电子工业出版社',24),(113388,'TR7/90','大学英语','胡玲','清华大学出版社',13),(114455,'TR9/12','线性代数','孙业','北京大学出版社',21),(118801,'TP4/15','计算机网络','黄力钧','高等教育出版社',22),(118802,'TP4/15','计算机网络','黄力钧','高等教育出版社',22),(332211,'TP5/10','计算机基础','李伟','高等教育出版社',18),(445501,'TP3/12','数据库导论','王强','科学出版社',18),(445502,'TP3/12','数据库导论','王强','科学出版社',18),(445503,'TP3/12','数据库导论','王强','科学出版社',18),(446601,'TP4/13','数据库基础','马凌云','人民邮电出版社',23),(446602,'TP4/13','数据库基础','马凌云','人民邮电出版社',23),(446603,'TP4/13','数据库基础','马凌云','人民邮电出版社',23),(449901,'TP4/14','FoxPro大全','周虹','科学出版社',33),(449902,'TP4/14','FoxPro大全','周虹','科学出版社',33),(665544,'TS7/21','高等数学','刘明','高等教育出版社',20);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-01  8:35:58
