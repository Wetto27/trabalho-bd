-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`empregado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`empregado` (
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`autores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autores` (
  `nome` VARCHAR(50) NOT NULL,
  `id` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`livros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`livros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(50) NOT NULL,
  `autores` VARCHAR(200) NOT NULL,
  `tema` VARCHAR(50) NOT NULL,
  `empregado_cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_livros_empregado1_idx` (`empregado_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_livros_empregado1`
    FOREIGN KEY (`empregado_cpf`)
    REFERENCES `mydb`.`empregado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`clientes` (
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `empregado_cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `fk_clientes_empregado1_idx` (`empregado_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_clientes_empregado1`
    FOREIGN KEY (`empregado_cpf`)
    REFERENCES `mydb`.`empregado` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `livros` VARCHAR(50) NOT NULL,
  `data` VARCHAR(10) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `clientes_cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_historico_clientes1_idx` (`clientes_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_historico_clientes1`
    FOREIGN KEY (`clientes_cpf`)
    REFERENCES `mydb`.`clientes` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`autores_tem_livros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autores_tem_livros` (
  `autores_nome` VARCHAR(50) NOT NULL,
  `livros_id` INT NOT NULL,
  PRIMARY KEY (`autores_nome`, `livros_id`),
  INDEX `fk_autores_has_livros_livros1_idx` (`livros_id` ASC) VISIBLE,
  INDEX `fk_autores_has_livros_autores1_idx` (`autores_nome` ASC) VISIBLE,
  CONSTRAINT `fk_autores_has_livros_autores1`
    FOREIGN KEY (`autores_nome`)
    REFERENCES `mydb`.`autores` (`nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autores_has_livros_livros1`
    FOREIGN KEY (`livros_id`)
    REFERENCES `mydb`.`livros` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
