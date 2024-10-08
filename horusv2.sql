-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: bdsgf
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `codigCategoria` varchar(10) NOT NULL,
  `nombreCategoria` varchar(50) NOT NULL,
  `idTipoGasto` int NOT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `idTipoGasto_idx` (`idTipoGasto`),
  CONSTRAINT `idTipoGasto` FOREIGN KEY (`idTipoGasto`) REFERENCES `tipos_gastos` (`idTipoGasto`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'ads','dsd',1),(2,'CAT001','Gastos de alimentación',1),(3,'CAT002','Gastos de transporte',1),(4,'CAT003','Gastos de entretenimiento',2),(5,'CAT004','Gastos de salud',2),(6,'CAT005','Gastos de educación',3),(7,'CAT006','Gastos de impuestos',3),(8,'CAT007','Gastos de servicios públicos',4),(9,'CAT008','Gastos de seguros',4);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificaciones_ingresos`
--

DROP TABLE IF EXISTS `clasificaciones_ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clasificaciones_ingresos` (
  `idClasificacionIngreso` int NOT NULL AUTO_INCREMENT,
  `nombreClasificacionIngreso` varchar(25) NOT NULL,
  PRIMARY KEY (`idClasificacionIngreso`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificaciones_ingresos`
--

LOCK TABLES `clasificaciones_ingresos` WRITE;
/*!40000 ALTER TABLE `clasificaciones_ingresos` DISABLE KEYS */;
INSERT INTO `clasificaciones_ingresos` VALUES (1,'dfsdfsd'),(2,'Salario'),(3,'Ingresos por ventas'),(4,'Honorarios'),(5,'Ingresos por alquiler'),(6,'Dividendos'),(7,'Bonificaciones'),(8,'Regalías'),(9,'Otros ingresos'),(10,'Otros ingresos 2'),(11,'Otros ingresos 2'),(12,'dfsdfsda');
/*!40000 ALTER TABLE `clasificaciones_ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_presupuesto`
--

DROP TABLE IF EXISTS `detalle_presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_presupuesto` (
  `idDetallePresupuesto` int NOT NULL AUTO_INCREMENT,
  `idPresupuesto` int NOT NULL,
  `idIngreso` int NOT NULL,
  `idGasto` int NOT NULL,
  PRIMARY KEY (`idDetallePresupuesto`),
  KEY `idPresupuesto_idx` (`idPresupuesto`),
  KEY `idIngreso_idx` (`idIngreso`),
  KEY `idGasto_idx` (`idGasto`),
  CONSTRAINT `idGasto` FOREIGN KEY (`idGasto`) REFERENCES `gastos` (`idGasto`),
  CONSTRAINT `idIngreso` FOREIGN KEY (`idIngreso`) REFERENCES `ingresos` (`idIngreso`),
  CONSTRAINT `idPresupuesto` FOREIGN KEY (`idPresupuesto`) REFERENCES `presupuestos` (`idPresupuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_presupuesto`
--

LOCK TABLES `detalle_presupuesto` WRITE;
/*!40000 ALTER TABLE `detalle_presupuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `nombreEstado` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'adsad'),(2,'1'),(3,'2'),(4,'Est01'),(5,'Est02');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuentes`
--

DROP TABLE IF EXISTS `fuentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fuentes` (
  `idFuente` int NOT NULL AUTO_INCREMENT,
  `codigoFuente` varchar(8) NOT NULL,
  `nombreFuente` varchar(50) NOT NULL,
  `origen` varchar(50) NOT NULL,
  `idClasificacionIngreso` int NOT NULL,
  PRIMARY KEY (`idFuente`),
  KEY `idClasificacionIngreso_idx` (`idClasificacionIngreso`),
  CONSTRAINT `idClasificacionIngreso` FOREIGN KEY (`idClasificacionIngreso`) REFERENCES `clasificaciones_ingresos` (`idClasificacionIngreso`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuentes`
--

LOCK TABLES `fuentes` WRITE;
/*!40000 ALTER TABLE `fuentes` DISABLE KEYS */;
INSERT INTO `fuentes` VALUES (1,'dfdf','dfdf','dffd',1),(3,'FUENTE01','Empresa X','Ventas de productos',2),(4,'FUENTE02','Consultoría ABC','Servicios de consultoría',3),(5,'FUENTE03','Propiedad XYZ','Alquiler de propiedad',4),(6,'FUENTE04','Dividendos Corp','Ingresos por dividendos',5),(7,'FUENTE05','Freelance Z','Honorarios por servicios',3),(8,'FUENTE06','Bonificaciones S.A.','Bonificaciones por desempeño',3),(9,'FUENTE07','Regalías Inc.','Regalías por derechos de autor',3),(10,'FUENTE08','Otros Ingresos Ltda.','Otros ingresos no especificados',2);
/*!40000 ALTER TABLE `fuentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `idGasto` int NOT NULL AUTO_INCREMENT,
  `codigoGasto` varchar(8) NOT NULL,
  `fechaGasto` date NOT NULL,
  `monto` double NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `id_Metodo_Pago` int NOT NULL,
  `id_Estado` int NOT NULL,
  `id_Categoria` int NOT NULL,
  PRIMARY KEY (`idGasto`),
  KEY `idEstado_idx` (`id_Estado`),
  KEY `id_Metodo_Pago_idx` (`id_Metodo_Pago`),
  KEY `id_Categoria_idx` (`id_Categoria`),
  CONSTRAINT `id_Categoria` FOREIGN KEY (`id_Categoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `id_Estado` FOREIGN KEY (`id_Estado`) REFERENCES `estados` (`idEstado`),
  CONSTRAINT `id_Metodo_Pago` FOREIGN KEY (`id_Metodo_Pago`) REFERENCES `metodos_pagos` (`idMetodoPago`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos`
--

LOCK TABLES `gastos` WRITE;
/*!40000 ALTER TABLE `gastos` DISABLE KEYS */;
INSERT INTO `gastos` VALUES (2,'srs4','2024-06-20',4444,'',1,1,1),(3,'wew1','2024-06-14',1111,'',1,1,1),(4,'43434','2024-06-12',55552222,'',1,1,1),(5,'sdsd','2024-08-13',1111,'',1,1,1),(6,'sas1','2024-06-19',111,'',1,1,1),(7,'32323','2024-06-12',331,'eiuu',1,1,1),(8,'111','2024-06-01',900,'sss',1,1,1),(9,'111','2024-06-05',22,'dasds',1,1,1),(10,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(15,'ll1','2024-06-14',900,'sss1s',1,1,1),(19,'ss1','2024-06-17',10000,'skdj444',4,1,1),(20,'codigo 2','2024-06-12',111,'sdsds',1,1,1),(21,'codigo 3','2024-06-12',111,'sdsds',1,1,1),(22,'ll1','2024-06-14',900,'sss1s',1,1,1),(23,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(24,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(25,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(26,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(27,'codigo 1','2024-06-12',111,'sdsds',1,1,1),(28,'32323','2024-06-12',331,'eiuu',1,1,1),(29,'ll134257','2024-06-14',900,'sss1s',1,2,2);
/*!40000 ALTER TABLE `gastos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingresos`
--

DROP TABLE IF EXISTS `ingresos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingresos` (
  `idIngreso` int NOT NULL AUTO_INCREMENT,
  `codigoIngreso` varchar(8) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `monto` double NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `idMetodoPago` int NOT NULL,
  `idEstado` int NOT NULL,
  `idFuente` int NOT NULL,
  PRIMARY KEY (`idIngreso`),
  KEY `idFuente_idx` (`idFuente`),
  KEY `idEstado_idx` (`idEstado`),
  KEY `idMetodoPago_idx` (`idMetodoPago`),
  CONSTRAINT `idEstado` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`),
  CONSTRAINT `idFuente` FOREIGN KEY (`idFuente`) REFERENCES `fuentes` (`idFuente`),
  CONSTRAINT `idMetodoPago` FOREIGN KEY (`idMetodoPago`) REFERENCES `metodos_pagos` (`idMetodoPago`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingresos`
--

LOCK TABLES `ingresos` WRITE;
/*!40000 ALTER TABLE `ingresos` DISABLE KEYS */;
INSERT INTO `ingresos` VALUES (2,'gtgtg','2024-06-13',5555,'',1,1,1),(3,'cod01','2024-06-30',10000,'Este',1,1,1),(8,'cod01','2024-06-30',10000,'Este',1,1,1);
/*!40000 ALTER TABLE `ingresos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodos_pagos`
--

DROP TABLE IF EXISTS `metodos_pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodos_pagos` (
  `idMetodoPago` int NOT NULL AUTO_INCREMENT,
  `nombreMetodoPago` varchar(45) NOT NULL,
  PRIMARY KEY (`idMetodoPago`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodos_pagos`
--

LOCK TABLES `metodos_pagos` WRITE;
/*!40000 ALTER TABLE `metodos_pagos` DISABLE KEYS */;
INSERT INTO `metodos_pagos` VALUES (1,'dfdf'),(2,'Tarjeta de crédito'),(3,'Transferencia bancaria'),(4,'Efectivo'),(5,'Cheque'),(6,'Pago móvil'),(7,'Letra de cambio'),(8,'PayPal'),(9,'Criptomoneda'),(10,'Criptomoneda l'),(11,''),(12,''),(13,'dfdf');
/*!40000 ALTER TABLE `metodos_pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodos`
--

DROP TABLE IF EXISTS `periodos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodos` (
  `idPeriodo` int NOT NULL AUTO_INCREMENT,
  `periodo` varchar(15) NOT NULL,
  PRIMARY KEY (`idPeriodo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodos`
--

LOCK TABLES `periodos` WRITE;
/*!40000 ALTER TABLE `periodos` DISABLE KEYS */;
INSERT INTO `periodos` VALUES (1,'df'),(2,'h'),(3,'hee'),(4,'dsdsdsd'),(5,'Periodo X'),(6,'Periodo XI'),(7,''),(8,''),(9,'Periodo XI');
/*!40000 ALTER TABLE `periodos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuestos`
--

DROP TABLE IF EXISTS `presupuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuestos` (
  `idPresupuesto` int NOT NULL AUTO_INCREMENT,
  `codigoPresupuesto` varchar(8) NOT NULL,
  `objetivo` varchar(100) NOT NULL,
  `idPeriodo` int NOT NULL,
  PRIMARY KEY (`idPresupuesto`),
  KEY `idPeriodo_idx` (`idPeriodo`),
  CONSTRAINT `idPeriodo` FOREIGN KEY (`idPeriodo`) REFERENCES `periodos` (`idPeriodo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuestos`
--

LOCK TABLES `presupuestos` WRITE;
/*!40000 ALTER TABLE `presupuestos` DISABLE KEYS */;
INSERT INTO `presupuestos` VALUES (1,'cod101','este es para.',1),(2,'cod101','este es para.2',1),(3,'cod101','este',1),(4,'cod102','este es',1),(5,'cod103','este',1),(6,'cod104','este',1),(7,'cod105','este',2),(8,'cod105','este',2),(9,'cod105','este',1),(10,'cod','este',1),(11,'cod','este',1),(12,'cod','este',1),(13,'cod bla','este',1),(17,'CAT000','Gastos de',1),(18,'CAT008','Gastos de seguros 2',1),(19,'cod10211','este es',5);
/*!40000 ALTER TABLE `presupuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategorias`
--

DROP TABLE IF EXISTS `subcategorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategorias` (
  `idSubcategoria` int NOT NULL AUTO_INCREMENT,
  `nombreSubcategoria` varchar(45) NOT NULL,
  `idCategoria` int NOT NULL,
  PRIMARY KEY (`idSubcategoria`),
  KEY `idCategoria_idx` (`idCategoria`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategorias`
--

LOCK TABLES `subcategorias` WRITE;
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` VALUES (1,'SubCat0',2),(2,'SubCat01',1),(3,'',1),(4,'',1),(5,'',1);
/*!40000 ALTER TABLE `subcategorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_gastos`
--

DROP TABLE IF EXISTS `tipos_gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_gastos` (
  `idTipoGasto` int NOT NULL AUTO_INCREMENT,
  `nombreTipoGasto` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idTipoGasto`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_gastos`
--

LOCK TABLES `tipos_gastos` WRITE;
/*!40000 ALTER TABLE `tipos_gastos` DISABLE KEYS */;
INSERT INTO `tipos_gastos` VALUES (1,'dfsfsdf'),(2,'Operativo'),(3,'Discrecional'),(4,'Fijo'),(5,'Variable'),(6,'Operativo'),(7,'Discrecional'),(8,'Fijo'),(9,'Variable'),(10,'Operativo'),(11,'Discrecional'),(12,'Fijo'),(13,'Variable'),(14,'Fijo 2'),(15,'Fijo 2'),(16,'Fijo 223'),(17,'Fijo 3'),(18,''),(19,'-1'),(20,'');
/*!40000 ALTER TABLE `tipos_gastos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `codigoUsuario` varchar(8) NOT NULL,
  `user` varchar(45) NOT NULL,
  `password` varchar(70) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `rol` int DEFAULT NULL,
  `otp` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'1','gab','123','gab@gmail.com',1,NULL),(2,'User1','gabs','123','gabs@gmail.com',1,NULL),(3,'User12','ad','123','gabs',2,NULL),(7,'User1200','Admin2','$2a$10$FMn0/0SI/MMQ2SdjZsN2kezKGvDd3OhY1XpsjTmd9OWceX4IhqxjG','gabrielarchaga2003@gmail.com',2,'529921'),(8,'User0001','Admin','$2a$10$ke6LvMqRdRCxOEO6nq6VBOlRCFy5DYPlcrK8IDBhSUuBYMfPXQBfW','gabs@gmail.com',2,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-30 14:30:38
