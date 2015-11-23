-- MySQL Script generated by MySQL Workbench
-- Thu Oct 22 22:49:49 2015
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema theaterpro
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema theaterpro
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `theaterpro` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `theaterpro` ;

-- -----------------------------------------------------
-- Table `theaterpro`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`user` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`user` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(40) NULL,
  `LastName` VARCHAR(40) NULL,
  `Email` VARCHAR(100) NULL,
  `isAdmin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`UserID`) ,
  UNIQUE INDEX `UserID_UNIQUE` (`UserID` ASC));


-- -----------------------------------------------------
-- Table `theaterpro`.`screen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`screen` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`screen` (
  `ScreenID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(40) NULL,
  `Size` INT NULL,
  `IMAX` TINYINT(1) NULL,
  `3D` TINYINT(1) NULL,
  `XD` TINYINT(1) NULL,
  `DBOX` TINYINT(1) NULL,
  PRIMARY KEY (`ScreenID`) ,
  UNIQUE INDEX `ScreenID_UNIQUE` (`ScreenID` ASC))
;


-- -----------------------------------------------------
-- Table `theaterpro`.`friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`friend` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`friend` (
  `UserID` INT NULL,
  `FriendID` INT NULL,
  INDEX `UserID_idx` (`UserID` ASC),
  INDEX `FriendID_idx` (`FriendID` ASC),
  CONSTRAINT `UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `theaterpro`.`user` (`UserID`),
  CONSTRAINT `FriendID`
    FOREIGN KEY (`FriendID`)
    REFERENCES `theaterpro`.`user` (`UserID`))
;


-- -----------------------------------------------------
-- Table `theaterpro`.`showing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`showing` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`showing` (
  `ShowingID` INT NOT NULL AUTO_INCREMENT,
  `ScreenID` INT NULL,
  `imdbID` VARCHAR(45) NULL,
  `startTimestamp` DATETIME NULL,
  `endTimestamp` DATETIME NULL,
  PRIMARY KEY (`ShowingID`) ,
  UNIQUE INDEX `showingID_UNIQUE` (`ShowingID` ASC) ,
  INDEX `ScreenID_idx` (`ScreenID` ASC) ,
  CONSTRAINT `ScreenID`
    FOREIGN KEY (`ScreenID`)
    REFERENCES `theaterpro`.`screen` (`ScreenID`))
;

-- -----------------------------------------------------
-- Table `theaterpro`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`ticket` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`ticket` (
  `UserID` INT NULL,
  `ShowingID` INT NULL,
  `updatedAt` TIMESTAMP NOT NULL,
  INDEX `UserID_idx` (`UserID` ASC) ,
  INDEX `ShowingID_idx` (`ShowingID` ASC) ,
  INDEX `updatedAt_idx` (`updatedAt` ASC) ,
  CONSTRAINT `TicketHolderID`
    FOREIGN KEY (`UserID`)
    REFERENCES `theaterpro`.`user` (`UserID`),
  CONSTRAINT `ShowingID`
    FOREIGN KEY (`ShowingID`)
    REFERENCES `theaterpro`.`showingarchive` (`ShowingID`))
;

-- -----------------------------------------------------
-- Table `theaterpro`.`ticketArchive`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`ticketArchive` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`ticketArchive` (
  `UserID` INT NULL,
  `ShowingID` INT NULL,
  INDEX `UserID_idx` (`UserID` ASC) ,
  INDEX `ShowingID_idx` (`ShowingID` ASC) ,
  CONSTRAINT `TicketHolderIDArchive`
    FOREIGN KEY (`UserID`)
    REFERENCES `theaterpro`.`user` (`UserID`),
  CONSTRAINT `ShowingIDArchive`
    FOREIGN KEY (`ShowingID`)
    REFERENCES `theaterpro`.`showingarchive` (`ShowingID`))
;

-- -----------------------------------------------------
-- Table `theaterpro`.`auth`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `theaterpro`.`auth` ;

CREATE TABLE IF NOT EXISTS `theaterpro`.`auth` (
  `UserID` INT NOT NULL,
  `hPassword` INT NULL,
  INDEX `UserID_idx` (`UserID` ASC) ,
  CONSTRAINT `AuthID`
    FOREIGN KEY (`UserID`)
    REFERENCES `theaterpro`.`user` (`UserID`))
;

-- -----------------------------------------------------
-- View friendView
-- -----------------------------------------------------
DROP VIEW IF EXISTS `theaterpro`.`friendView`;

CREATE VIEW friendView AS
  SELECT friend.UserID, user.UserID AS FriendID, user.FirstName, user.LastName, user.Email FROM
  friend, user
  WHERE
  friendID = user.UserID
  ORDER BY user.LastName ASC, user.FirstName ASC
;


-- -----------------------------------------------------
-- Trigger deleteFriendTrigger
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS deleteFriendTrigger;

DELIMITER //
CREATE TRIGGER deleteFriendTrigger
BEFORE DELETE ON user FOR EACH ROW
BEGIN
DELETE FROM friend WHERE old.UserID = UserID;
DELETE FROM friend WHERE old.UserID = FriendID;
END //
DELIMITER ;

-- -----------------------------------------------------
-- Procedure getAllUsers()
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS getAllUsers;
DELIMITER //
CREATE PROCEDURE getAllUsers ()
BEGIN
  SELECT * FROM user;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS ArchiveTickets;
delimiter //
CREATE Procedure ArchiveTickets(IN cutOff TIMESTAMP)
Begin
start transaction;
Insert into ticketArchive
Select UserID, ShowingID
from ticket where updatedAt < cutOff;
Delete from ticket where updatedAt < cutOff; commit; end; //
delimiter ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
