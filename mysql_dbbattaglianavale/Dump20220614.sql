CREATE DATABASE  IF NOT EXISTS `dbbattaglianavale` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbbattaglianavale`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbbattaglianavale
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
-- Table structure for table `bersaglio`
--

DROP TABLE IF EXISTS `bersaglio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bersaglio` (
  `numRiga` int NOT NULL,
  `numColonna` int NOT NULL,
  `idPartita` int NOT NULL,
  KEY `idPartita_idx` (`idPartita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bersaglio`
--

LOCK TABLES `bersaglio` WRITE;
/*!40000 ALTER TABLE `bersaglio` DISABLE KEYS */;
INSERT INTO `bersaglio` VALUES (2,2,0),(3,3,1),(2,2,2);
/*!40000 ALTER TABLE `bersaglio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `griglia`
--

DROP TABLE IF EXISTS `griglia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `griglia` (
  `numRiga` int NOT NULL,
  `numColonna` int NOT NULL,
  `idPartita` int NOT NULL,
  PRIMARY KEY (`idPartita`),
  CONSTRAINT `idPartita` FOREIGN KEY (`idPartita`) REFERENCES `partite` (`idpartita`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `griglia`
--

LOCK TABLES `griglia` WRITE;
/*!40000 ALTER TABLE `griglia` DISABLE KEYS */;
INSERT INTO `griglia` VALUES (3,3,0),(7,7,1),(4,4,2);
/*!40000 ALTER TABLE `griglia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partite`
--

DROP TABLE IF EXISTS `partite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partite` (
  `idpartita` int NOT NULL,
  `numtentativimax` int NOT NULL,
  `nomeGiocatore` varchar(20) NOT NULL,
  `nomeVincitore` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idpartita`,`nomeGiocatore`),
  KEY `nomegiocatore_idx` (`nomeGiocatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partite`
--

LOCK TABLES `partite` WRITE;
/*!40000 ALTER TABLE `partite` DISABLE KEYS */;
INSERT INTO `partite` VALUES (0,3,'alfonso','fabio'),(0,3,'antobio','fabio'),(0,3,'fabio','fabio'),(1,6,'andrea','andrea'),(1,6,'giovanni','andrea'),(1,6,'mario','andrea'),(2,6,'gennaro ','gennaro '),(2,6,'nicola','gennaro '),(2,6,'saverio ','gennaro ');
/*!40000 ALTER TABLE `partite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES ('alfonso'),('andrea'),('antobio'),('antoni'),('fabio'),('gennaro '),('giovanni'),('mario'),('nicola'),('saverio ');
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-14 12:09:45
