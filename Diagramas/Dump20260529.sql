-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion_paquetes
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `asigna_paquete`
--

DROP TABLE IF EXISTS `asigna_paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asigna_paquete` (
  `idAsignaPaquete` int NOT NULL AUTO_INCREMENT,
  `paquete` int NOT NULL,
  `repartidor` int NOT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `fechaAsignada` datetime NOT NULL,
  PRIMARY KEY (`idAsignaPaquete`),
  KEY `idRepartidor_idx` (`repartidor`),
  KEY `idPaquete` (`paquete`),
  CONSTRAINT `idPaquete` FOREIGN KEY (`paquete`) REFERENCES `paquete` (`codigo_unico`),
  CONSTRAINT `idRepartidor` FOREIGN KEY (`repartidor`) REFERENCES `repartidor` (`idRepartidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(200) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `idRecepcionista` int NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `fk_Cliente_Persona1_idx` (`cedula`),
  KEY `fk_Cliente_Recepcionista1_idx` (`idRecepcionista`),
  CONSTRAINT `fk_Cliente_Persona1` FOREIGN KEY (`cedula`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `fk_Cliente_Recepcionista1` FOREIGN KEY (`idRecepcionista`) REFERENCES `recepcionista` (`idRecepcionista`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `despacha`
--

DROP TABLE IF EXISTS `despacha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `despacha` (
  `fecha_hora` datetime NOT NULL,
  `codigo_unico` int NOT NULL,
  `idOperador` int NOT NULL,
  PRIMARY KEY (`codigo_unico`,`idOperador`),
  KEY `fk_Despacha_Paquete1_idx` (`codigo_unico`),
  KEY `fk_Despacha_Operador_Despacho1_idx` (`idOperador`),
  CONSTRAINT `fk_Despacha_Operador_Despacho1` FOREIGN KEY (`idOperador`) REFERENCES `operador_despacho` (`idOperador`),
  CONSTRAINT `fk_Despacha_Paquete1` FOREIGN KEY (`codigo_unico`) REFERENCES `paquete` (`codigo_unico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega` (
  `nombre_rec` varchar(45) NOT NULL,
  `observaciones` varchar(45) DEFAULT NULL,
  `fecha_hora` datetime NOT NULL,
  `codigo_unico` int NOT NULL,
  `idRepartidor` int NOT NULL,
  PRIMARY KEY (`idRepartidor`,`codigo_unico`),
  KEY `fk_Entrega_Paquete1_idx` (`codigo_unico`),
  KEY `fk_Entrega_Repartidor1_idx` (`idRepartidor`),
  CONSTRAINT `fk_Entrega_Paquete1` FOREIGN KEY (`codigo_unico`) REFERENCES `paquete` (`codigo_unico`),
  CONSTRAINT `fk_Entrega_Repartidor1` FOREIGN KEY (`idRepartidor`) REFERENCES `repartidor` (`idRepartidor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `idFactura` int NOT NULL AUTO_INCREMENT,
  `nro_factura` varchar(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `iva` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `codigo_unico` int NOT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `fk_Factura_Paquete1_idx` (`codigo_unico`),
  CONSTRAINT `fk_Factura_Paquete1` FOREIGN KEY (`codigo_unico`) REFERENCES `paquete` (`codigo_unico`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historial_estado`
--

DROP TABLE IF EXISTS `historial_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_estado` (
  `idHistorial` int NOT NULL AUTO_INCREMENT,
  `observaciones` varchar(200) DEFAULT NULL,
  `estado` enum('Receptado','En Transito','Entregado') NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `codigo_unico` int NOT NULL,
  `ubicacion` int NOT NULL,
  PRIMARY KEY (`idHistorial`),
  UNIQUE KEY `idHistorial_UNIQUE` (`idHistorial`),
  KEY `fk_Historial_Estado_Paquete1_idx` (`codigo_unico`),
  KEY `fk_Historial_Estado_Ubicacion1_idx` (`ubicacion`),
  CONSTRAINT `fk_Historial_Estado_Paquete1` FOREIGN KEY (`codigo_unico`) REFERENCES `paquete` (`codigo_unico`),
  CONSTRAINT `fk_Historial_Estado_Ubicacion1` FOREIGN KEY (`ubicacion`) REFERENCES `ubicacion` (`idLocal`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operador_despacho`
--

DROP TABLE IF EXISTS `operador_despacho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operador_despacho` (
  `idOperador` int NOT NULL AUTO_INCREMENT,
  `cedula` varchar(45) NOT NULL,
  `bodega` int NOT NULL,
  PRIMARY KEY (`idOperador`),
  KEY `fk_Operador_Despacho_Persona1_idx` (`cedula`),
  KEY `fk_Operador_Despacho_Ubicacion1_idx` (`bodega`),
  CONSTRAINT `fk_Operador_Despacho_Persona1` FOREIGN KEY (`cedula`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `fk_Operador_Despacho_Ubicacion1` FOREIGN KEY (`bodega`) REFERENCES `ubicacion` (`idLocal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paquete`
--

DROP TABLE IF EXISTS `paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete` (
  `codigo_unico` int NOT NULL AUTO_INCREMENT,
  `peso` decimal(10,2) DEFAULT NULL,
  `tipo_envio` varchar(45) DEFAULT NULL,
  `estado` enum('Receptado','En Transito','Entregado') NOT NULL,
  `ciudad_envio` varchar(45) NOT NULL,
  `direccion_entrega` varchar(45) NOT NULL,
  `ciudad_destino` varchar(45) NOT NULL,
  `nro_seguimiento` varchar(20) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `idRecepcionista` int NOT NULL,
  `idCliente` int NOT NULL,
  `idTarifa` int NOT NULL,
  `destinatarioNomb` varchar(45) NOT NULL,
  `destinatarioTel` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo_unico`),
  UNIQUE KEY `nro_seguimiento_UNIQUE` (`nro_seguimiento`),
  KEY `fk_Paquete_Recepcionista1_idx` (`idRecepcionista`),
  KEY `fk_Paquete_Cliente1_idx` (`idCliente`),
  KEY `fk_Paquete_Tarifa1_idx` (`idTarifa`),
  CONSTRAINT `fk_Paquete_Cliente1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `fk_Paquete_Recepcionista1` FOREIGN KEY (`idRecepcionista`) REFERENCES `recepcionista` (`idRecepcionista`),
  CONSTRAINT `fk_Paquete_Tarifa1` FOREIGN KEY (`idTarifa`) REFERENCES `tarifa` (`idTarifa`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `cedula` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `numero` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recepcionista`
--

DROP TABLE IF EXISTS `recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recepcionista` (
  `idRecepcionista` int NOT NULL AUTO_INCREMENT,
  `turno` enum('Mañana','Tarde') NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `local` int NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  KEY `fk_Recepcionista_Persona1_idx` (`cedula`),
  KEY `fk_Recepcionista_Ubicacion1_idx` (`local`),
  CONSTRAINT `fk_Recepcionista_Persona1` FOREIGN KEY (`cedula`) REFERENCES `persona` (`cedula`),
  CONSTRAINT `fk_Recepcionista_Ubicacion1` FOREIGN KEY (`local`) REFERENCES `ubicacion` (`idLocal`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `repartidor`
--

DROP TABLE IF EXISTS `repartidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repartidor` (
  `idRepartidor` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(45) NOT NULL,
  `vehiculo` varchar(45) DEFAULT NULL,
  `estado` enum('Activo','Inactivo') NOT NULL,
  `cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idRepartidor`),
  KEY `fk_Repartidor_Persona_idx` (`cedula`),
  CONSTRAINT `fk_Repartidor_Persona` FOREIGN KEY (`cedula`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supervisor`
--

DROP TABLE IF EXISTS `supervisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supervisor` (
  `idSupervisor` int NOT NULL AUTO_INCREMENT,
  `cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idSupervisor`),
  KEY `fk_Supervisor_Persona1_idx` (`cedula`),
  CONSTRAINT `fk_Supervisor_Persona1` FOREIGN KEY (`cedula`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarifa` (
  `idTarifa` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `precio_base` decimal(10,2) NOT NULL,
  `kg_extra` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idTarifa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ubicacion`
--

DROP TABLE IF EXISTS `ubicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubicacion` (
  `idLocal` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `tipo` enum('Bodega','Local','Punto de Retiro') NOT NULL,
  PRIMARY KEY (`idLocal`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-29 17:22:05
