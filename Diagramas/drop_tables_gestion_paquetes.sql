-- =============================================================
--  Script DROP TABLES - gestion_paquetes
--  Orden inverso al de creación para respetar FK
-- =============================================================

USE `gestion_paquetes`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `gestion_paquetes`.`Factura`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Entrega`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Despacha`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Historial_Estado`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Paquete`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Tarifa`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Cliente`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Operador_Despacho`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Recepcionista`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Ubicacion`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Repartidor`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Supervisor`;
DROP TABLE IF EXISTS `gestion_paquetes`.`Persona`;

SET FOREIGN_KEY_CHECKS = 1;
