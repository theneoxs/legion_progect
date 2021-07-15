-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Coach`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Coach` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Coach` (
  `idCoach` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `telephone` INT NOT NULL,
  `age` INT NOT NULL,
  `rank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCoach`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Group` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Group` (
  `idGroup` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `description` MEDIUMTEXT NULL,
  `Coach_idCoach` INT NOT NULL,
  PRIMARY KEY (`idGroup`),
  INDEX `fk_Group_Coach1_idx` (`Coach_idCoach` ASC),
  CONSTRAINT `fk_Group_Coach1`
    FOREIGN KEY (`Coach_idCoach`)
    REFERENCES `mydb`.`Coach` (`idCoach`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Paytype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Paytype` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Paytype` (
  `variant` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`variant`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Sportsman`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Sportsman` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Sportsman` (
  `idSportsman` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `telephone` INT NOT NULL,
  `age` INT NOT NULL,
  `weight` INT NOT NULL,
  PRIMARY KEY (`idSportsman`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Abonement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Abonement` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Abonement` (
  `idAbonement` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `lastDate` DATE NOT NULL,
  `trainCount` INT NOT NULL,
  `Group_idGroup` INT NOT NULL,
  `Paytype_variant` VARCHAR(50) NOT NULL,
  `Sportsman_idSportsman` INT NOT NULL,
  PRIMARY KEY (`idAbonement`),
  INDEX `fk_Abonement_Group1_idx` (`Group_idGroup` ASC),
  INDEX `fk_Abonement_Paytype1_idx` (`Paytype_variant` ASC),
  INDEX `fk_Abonement_Sportsman1_idx` (`Sportsman_idSportsman` ASC),
  CONSTRAINT `fk_Abonement_Group1`
    FOREIGN KEY (`Group_idGroup`)
    REFERENCES `mydb`.`Group` (`idGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abonement_Paytype1`
    FOREIGN KEY (`Paytype_variant`)
    REFERENCES `mydb`.`Paytype` (`variant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abonement_Sportsman1`
    FOREIGN KEY (`Sportsman_idSportsman`)
    REFERENCES `mydb`.`Sportsman` (`idSportsman`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`History`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`History` ;

CREATE TABLE IF NOT EXISTS `mydb`.`History` (
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `Abonement_idAbonement` INT NOT NULL,
  CONSTRAINT `fk_History_Abonement`
    FOREIGN KEY (`Abonement_idAbonement`)
    REFERENCES `mydb`.`Abonement` (`idAbonement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Train` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Train` (
  `idTrain` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NULL,
  PRIMARY KEY (`idTrain`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Group_has_Train`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Group_has_Train` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Group_has_Train` (
  `Group_idGroup` INT NOT NULL,
  `Train_idTrain` INT NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  INDEX `fk_Group_has_Train_Train1_idx` (`Train_idTrain` ASC),
  INDEX `fk_Group_has_Train_Group1_idx` (`Group_idGroup` ASC),
  CONSTRAINT `fk_Group_has_Train_Group1`
    FOREIGN KEY (`Group_idGroup`)
    REFERENCES `mydb`.`Group` (`idGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Group_has_Train_Train1`
    FOREIGN KEY (`Train_idTrain`)
    REFERENCES `mydb`.`Train` (`idTrain`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Reward` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Reward` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Competition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Competition` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Competition` (
  `idCompetition` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `Reward_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCompetition`),
  INDEX `fk_Competition_Reward1_idx` (`Reward_name` ASC),
  CONSTRAINT `fk_Competition_Reward1`
    FOREIGN KEY (`Reward_name`)
    REFERENCES `mydb`.`Reward` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Competition_has_Sportsman`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Competition_has_Sportsman` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Competition_has_Sportsman` (
  `Competition_idCompetition` INT NOT NULL,
  `Sportsman_idSportsman` INT NOT NULL,
  PRIMARY KEY (`Competition_idCompetition`, `Sportsman_idSportsman`),
  INDEX `fk_Competition_has_Sportsman_Sportsman1_idx` (`Sportsman_idSportsman` ASC),
  INDEX `fk_Competition_has_Sportsman_Competition1_idx` (`Competition_idCompetition` ASC),
  CONSTRAINT `fk_Competition_has_Sportsman_Competition1`
    FOREIGN KEY (`Competition_idCompetition`)
    REFERENCES `mydb`.`Competition` (`idCompetition`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Competition_has_Sportsman_Sportsman1`
    FOREIGN KEY (`Sportsman_idSportsman`)
    REFERENCES `mydb`.`Sportsman` (`idSportsman`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Coach`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Coach` (`idCoach`, `name`, `surname`, `lastname`, `telephone`, `age`, `rank`) VALUES (1, 'Aboba', 'Kekov', 'Tagirovich', 555111, 56, 'S');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Group`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Group` (`idGroup`, `number`, `description`, `Coach_idCoach`) VALUES (1, 15, 'Test group', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Paytype`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Paytype` (`variant`) VALUES ('Card');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Sportsman`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Sportsman` (`idSportsman`, `name`, `surname`, `lastname`, `telephone`, `age`, `weight`) VALUES (1, 'Artem', 'Makarov', 'Sanich', 5553535, 20, 58);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Abonement`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Abonement` (`idAbonement`, `date`, `lastDate`, `trainCount`, `Group_idGroup`, `Paytype_variant`, `Sportsman_idSportsman`) VALUES (1, '2020.01.11', '2020.02.11', 10, 1, 'Card', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Train`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Train` (`idTrain`, `name`, `description`) VALUES (1, 'test Train', 'This is simple training. Stand training');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Group_has_Train`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Group_has_Train` (`Group_idGroup`, `Train_idTrain`, `date`, `time`) VALUES (1, 1, '2020-05-11', '11.20');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Reward`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Reward` (`name`) VALUES ('Medal');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Competition`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Competition` (`idCompetition`, `name`, `date`, `Reward_name`) VALUES (1, 'Fight', '2020-05-30', 'Medal');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Competition_has_Sportsman`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Competition_has_Sportsman` (`Competition_idCompetition`, `Sportsman_idSportsman`) VALUES (1, 1);

COMMIT;

