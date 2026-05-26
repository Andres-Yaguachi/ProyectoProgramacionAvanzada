-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestion_paquetes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestion_paquetes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestion_paquetes` DEFAULT CHARACTER SET utf8 ;
USE `gestion_paquetes` ;

-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Persona` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `numero` VARCHAR(20) NULL,
  `password` VARCHAR(255) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Supervisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Supervisor` (
  `idSupervisor` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSupervisor`),
  INDEX `fk_Supervisor_Persona1_idx` (`cedula` ASC) VISIBLE,
  CONSTRAINT `fk_Supervisor_Persona1`
    FOREIGN KEY (`cedula`)
    REFERENCES `gestion_paquetes`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Repartidor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Repartidor` (
  `idRepartidor` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(45) NOT NULL,
  `vehiculo` VARCHAR(45) NULL,
  `estado` ENUM('Activo', 'Inactivo') NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRepartidor`),
  INDEX `fk_Repartidor_Persona_idx` (`cedula` ASC) VISIBLE,
  CONSTRAINT `fk_Repartidor_Persona`
    FOREIGN KEY (`cedula`)
    REFERENCES `gestion_paquetes`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Ubicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Ubicacion` (
  `idLocal` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `tipo` ENUM('Bodega', 'Local', 'Punto de Retiro') NOT NULL,
  PRIMARY KEY (`idLocal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Recepcionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Recepcionista` (
  `idRecepcionista` INT NOT NULL AUTO_INCREMENT,
  `turno` ENUM('Mañana', 'Tarde') NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `local` INT NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  INDEX `fk_Recepcionista_Persona1_idx` (`cedula` ASC) VISIBLE,
  INDEX `fk_Recepcionista_Ubicacion1_idx` (`local` ASC) VISIBLE,
  CONSTRAINT `fk_Recepcionista_Persona1`
    FOREIGN KEY (`cedula`)
    REFERENCES `gestion_paquetes`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recepcionista_Ubicacion1`
    FOREIGN KEY (`local`)
    REFERENCES `gestion_paquetes`.`Ubicacion` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Operador_Despacho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Operador_Despacho` (
  `idOperador` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  `bodega` INT NOT NULL,
  PRIMARY KEY (`idOperador`),
  INDEX `fk_Operador_Despacho_Persona1_idx` (`cedula` ASC) VISIBLE,
  INDEX `fk_Operador_Despacho_Ubicacion1_idx` (`bodega` ASC) VISIBLE,
  CONSTRAINT `fk_Operador_Despacho_Persona1`
    FOREIGN KEY (`cedula`)
    REFERENCES `gestion_paquetes`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Operador_Despacho_Ubicacion1`
    FOREIGN KEY (`bodega`)
    REFERENCES `gestion_paquetes`.`Ubicacion` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  `idRecepcionista` INT NOT NULL,
  PRIMARY KEY (`idCliente`),
  INDEX `fk_Cliente_Persona1_idx` (`cedula` ASC) VISIBLE,
  INDEX `fk_Cliente_Recepcionista1_idx` (`idRecepcionista` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Persona1`
    FOREIGN KEY (`cedula`)
    REFERENCES `gestion_paquetes`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cliente_Recepcionista1`
    FOREIGN KEY (`idRecepcionista`)
    REFERENCES `gestion_paquetes`.`Recepcionista` (`idRecepcionista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Tarifa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Tarifa` (
  `idTarifa` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `precio_base` DECIMAL(10,2) NOT NULL,
  `kg_extra` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idTarifa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Paquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Paquete` (
  `codigo_unico` INT NOT NULL AUTO_INCREMENT,
  `peso` DECIMAL(10,2) NULL,
  `tipo_envio` VARCHAR(45) NULL,
  `estado` ENUM('Receptado', 'En Transito', 'Entregado') NOT NULL,
  `ciudad_envio` VARCHAR(45) NOT NULL,
  `direccion_entrega` VARCHAR(45) NOT NULL,
  `ciudad_destino` VARCHAR(45) NOT NULL,
  `nro_seguimiento` VARCHAR(20) NOT NULL,
  `fecha_hora` DATETIME NOT NULL,
  `idRecepcionista` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `idTarifa` INT NOT NULL,
  `destinatarioNomb` VARCHAR(45) NOT NULL,
  `destinatarioTel` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`codigo_unico`),
  UNIQUE INDEX `nro_seguimiento_UNIQUE` (`nro_seguimiento` ASC) VISIBLE,
  INDEX `fk_Paquete_Recepcionista1_idx` (`idRecepcionista` ASC) VISIBLE,
  INDEX `fk_Paquete_Cliente1_idx` (`idCliente` ASC) VISIBLE,
  INDEX `fk_Paquete_Tarifa1_idx` (`idTarifa` ASC) VISIBLE,
  CONSTRAINT `fk_Paquete_Recepcionista1`
    FOREIGN KEY (`idRecepcionista`)
    REFERENCES `gestion_paquetes`.`Recepcionista` (`idRecepcionista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paquete_Cliente1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `gestion_paquetes`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paquete_Tarifa1`
    FOREIGN KEY (`idTarifa`)
    REFERENCES `gestion_paquetes`.`Tarifa` (`idTarifa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Historial_Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Historial_Estado` (
  `idHistorial` INT NOT NULL AUTO_INCREMENT,
  `observaciones` VARCHAR(45) NULL,
  `estado` ENUM('Receptado', 'En Transito', 'Entregado') NOT NULL,
  `fecha_hora` DATETIME NOT NULL,
  `codigo_unico` INT NOT NULL,
  `ubicacion` INT NOT NULL,
  PRIMARY KEY (`idHistorial`),
  UNIQUE INDEX `idHistorial_UNIQUE` (`idHistorial` ASC) VISIBLE,
  INDEX `fk_Historial_Estado_Paquete1_idx` (`codigo_unico` ASC) VISIBLE,
  INDEX `fk_Historial_Estado_Ubicacion1_idx` (`ubicacion` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Estado_Paquete1`
    FOREIGN KEY (`codigo_unico`)
    REFERENCES `gestion_paquetes`.`Paquete` (`codigo_unico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Estado_Ubicacion1`
    FOREIGN KEY (`ubicacion`)
    REFERENCES `gestion_paquetes`.`Ubicacion` (`idLocal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Despacha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Despacha` (
  `fecha_hora` DATETIME NOT NULL,
  `codigo_unico` INT NOT NULL,
  `idOperador` INT NOT NULL,
  INDEX `fk_Despacha_Paquete1_idx` (`codigo_unico` ASC) VISIBLE,
  INDEX `fk_Despacha_Operador_Despacho1_idx` (`idOperador` ASC) VISIBLE,
  PRIMARY KEY (`codigo_unico`, `idOperador`),
  CONSTRAINT `fk_Despacha_Paquete1`
    FOREIGN KEY (`codigo_unico`)
    REFERENCES `gestion_paquetes`.`Paquete` (`codigo_unico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Despacha_Operador_Despacho1`
    FOREIGN KEY (`idOperador`)
    REFERENCES `gestion_paquetes`.`Operador_Despacho` (`idOperador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Entrega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Entrega` (
  `nombre_rec` VARCHAR(45) NOT NULL,
  `observaciones` VARCHAR(45) NULL,
  `fecha_hora` DATETIME NOT NULL,
  `codigo_unico` INT NOT NULL,
  `idRepartidor` INT NOT NULL,
  INDEX `fk_Entrega_Paquete1_idx` (`codigo_unico` ASC) VISIBLE,
  INDEX `fk_Entrega_Repartidor1_idx` (`idRepartidor` ASC) VISIBLE,
  PRIMARY KEY (`idRepartidor`, `codigo_unico`),
  CONSTRAINT `fk_Entrega_Paquete1`
    FOREIGN KEY (`codigo_unico`)
    REFERENCES `gestion_paquetes`.`Paquete` (`codigo_unico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Entrega_Repartidor1`
    FOREIGN KEY (`idRepartidor`)
    REFERENCES `gestion_paquetes`.`Repartidor` (`idRepartidor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestion_paquetes`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion_paquetes`.`Factura` (
  `idFactura` INT NOT NULL AUTO_INCREMENT,
  `nro_factura` VARCHAR(20) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `subtotal` DECIMAL(10,2) NOT NULL,
  `iva` DECIMAL(10,2) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `codigo_unico` INT NOT NULL,
  PRIMARY KEY (`idFactura`),
  INDEX `fk_Factura_Paquete1_idx` (`codigo_unico` ASC) VISIBLE,
  CONSTRAINT `fk_Factura_Paquete1`
    FOREIGN KEY (`codigo_unico`)
    REFERENCES `gestion_paquetes`.`Paquete` (`codigo_unico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
