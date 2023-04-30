CREATE DATABASE IF NOT EXISTS core;

ALTER DATABASE core
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON core.* TO 'coreusr'@'%' IDENTIFIED BY 'corepwd';

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema core
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema core
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `core` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `core` ;

-- -----------------------------------------------------
-- Table `core`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`country` (
  `iso3` VARCHAR(255) NOT NULL,
  `iso2` VARCHAR(255) NOT NULL,
  `lat` DOUBLE NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`iso3`),
  UNIQUE INDEX `UK_h9ojhx8niamk5q3cpa1e1hvou` (`iso2` ASC) VISIBLE,
  UNIQUE INDEX `UK_llidyp77h6xkeokpbmoy710d4` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`city` (
  `id` INT NOT NULL,
  `lat` DOUBLE NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `postcode` VARCHAR(255) NULL DEFAULT NULL,
  `state_full_name` VARCHAR(255) NULL DEFAULT NULL,
  `state_short_code` VARCHAR(255) NULL DEFAULT NULL,
  `country_iso3` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_qsstlki7ni5ovaariyy9u8y79` (`name` ASC) VISIBLE,
  INDEX `FK3ct1vjyavjc2uf0xjufsl8v28` (`country_iso3` ASC) VISIBLE,
  CONSTRAINT `FK3ct1vjyavjc2uf0xjufsl8v28`
    FOREIGN KEY (`country_iso3`)
    REFERENCES `core`.`country` (`iso3`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`site` (
  `id` INT NOT NULL,
  `domain` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  `country_iso3` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_qsgk5cjl6wt1xvhdeqymoymqb` (`domain` ASC) VISIBLE,
  UNIQUE INDEX `UK_f9iil6uu8d9ohutpr2irlpvio` (`name` ASC) VISIBLE,
  INDEX `FKgdtllv3isrtb3hb9rtrhai948` (`country_iso3` ASC) VISIBLE,
  CONSTRAINT `FKgdtllv3isrtb3hb9rtrhai948`
    FOREIGN KEY (`country_iso3`)
    REFERENCES `core`.`country` (`iso3`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`centre` (
  `id` INT NOT NULL,
  `chairman_teacher_id` INT NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `full_image` VARCHAR(255) NULL DEFAULT NULL,
  `mobile` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NOT NULL,
  `thumb_image` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NOT NULL,
  `uri` VARCHAR(255) NULL DEFAULT NULL,
  `city_id` INT NOT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `FKn06twke55y9mnx613174vs9ed` (`city_id` ASC) VISIBLE,
  INDEX `FKwdcrdv6fpyrwvm1gnu3k76tt` (`site_id` ASC) VISIBLE,
  CONSTRAINT `FKn06twke55y9mnx613174vs9ed`
    FOREIGN KEY (`city_id`)
    REFERENCES `core`.`city` (`id`),
  CONSTRAINT `FKwdcrdv6fpyrwvm1gnu3k76tt`
    FOREIGN KEY (`site_id`)
    REFERENCES `core`.`site` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`centre_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`centre_settings` (
  `id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK9b2g4xuesai4hdwupxrso0wb6` (`centre_id` ASC) VISIBLE,
  CONSTRAINT `FK9b2g4xuesai4hdwupxrso0wb6`
    FOREIGN KEY (`centre_id`)
    REFERENCES `core`.`centre` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`site_centre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`site_centre` (
  `id` INT NOT NULL,
  `centre_id` INT NOT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4vlk1qlriris8wrri78q9x6pn` (`centre_id` ASC) VISIBLE,
  INDEX `FKdf1ihu7bqbncy7c4fguhuwd3y` (`site_id` ASC) VISIBLE,
  CONSTRAINT `FK4vlk1qlriris8wrri78q9x6pn`
    FOREIGN KEY (`centre_id`)
    REFERENCES `core`.`centre` (`id`),
  CONSTRAINT `FKdf1ihu7bqbncy7c4fguhuwd3y`
    FOREIGN KEY (`site_id`)
    REFERENCES `core`.`site` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`site_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`site_settings` (
  `id` INT NOT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKl8r9xkih3i7xspcupxa6inoa6` (`site_id` ASC) VISIBLE,
  CONSTRAINT `FKl8r9xkih3i7xspcupxa6inoa6`
    FOREIGN KEY (`site_id`)
    REFERENCES `core`.`site` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `core`.`venue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core`.`venue` (
  `id` INT NOT NULL,
  `address1` VARCHAR(255) NULL DEFAULT NULL,
  `address2` VARCHAR(255) NULL DEFAULT NULL,
  `address_notes` VARCHAR(255) NULL DEFAULT NULL,
  `brief_address` VARCHAR(255) NULL DEFAULT NULL,
  `lat` DOUBLE NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `post_code` VARCHAR(255) NULL DEFAULT NULL,
  `total_places` INT NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_9uyuoc3vs9lv1qpae6j8q40xe` (`name` ASC) VISIBLE,
  INDEX `FKjvb2os94uuou6bjriy78c8k7i` (`city_id` ASC) VISIBLE,
  CONSTRAINT `FKjvb2os94uuou6bjriy78c8k7i`
    FOREIGN KEY (`city_id`)
    REFERENCES `core`.`city` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
