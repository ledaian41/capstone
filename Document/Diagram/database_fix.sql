-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tbladmin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbladmin` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(115) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblcategory` (
  `categoryID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(95) NULL DEFAULT NULL,
  PRIMARY KEY (`categoryID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tlcity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tlcity` (
  `cityCode` VARCHAR(15) NOT NULL,
  `cityName` VARCHAR(105) NULL DEFAULT NULL,
  PRIMARY KEY (`cityCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblrole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblrole` (
  `roleID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`roleID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tbluser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbluser` (
  `userID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(75) NULL DEFAULT NULL,
  `password` VARCHAR(55) NULL DEFAULT NULL,
  `firstname` VARCHAR(40) NULL DEFAULT NULL,
  `lastname` VARCHAR(40) NULL DEFAULT NULL,
  `dateOfBirth` DATE NULL DEFAULT NULL,
  `registerDate` DATE NULL DEFAULT NULL,
  `phoneNumber` INT(11) NULL DEFAULT NULL,
  `gender` TINYINT(1) NULL DEFAULT NULL,
  `aboutMe` MEDIUMTEXT NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `primayryAddress` VARCHAR(255) NULL DEFAULT NULL,
  `sencondAddress` VARCHAR(255) NULL DEFAULT NULL,
  `cityCode` VARCHAR(15) NOT NULL,
  `roleID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_tblUser_tlCity1_idx` (`cityCode` ASC),
  INDEX `fk_tbluser_tblrole1_idx` (`roleID` ASC),
  CONSTRAINT `fk_tblUser_tlCity1`
    FOREIGN KEY (`cityCode`)
    REFERENCES `mydb`.`tlcity` (`cityCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbluser_tblrole1`
    FOREIGN KEY (`roleID`)
    REFERENCES `mydb`.`tblrole` (`roleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblideabook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblideabook` (
  `ideaBookID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(85) NULL DEFAULT NULL,
  `description` VARCHAR(555) NULL DEFAULT NULL,
  `isPublic` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ideaBookID`),
  INDEX `fk_tblIdeaBook_tblUser1_idx` (`userID` ASC),
  CONSTRAINT `fk_tblIdeaBook_tblUser1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblproject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblproject` (
  `projectID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `projectName` VARCHAR(155) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `cost` FLOAT UNSIGNED NULL DEFAULT NULL,
  `website` VARCHAR(145) NULL DEFAULT NULL,
  `year` YEAR NULL DEFAULT NULL,
  `keywords` VARCHAR(145) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`projectID`),
  INDEX `fk_tblProject_tblUser1_idx` (`userID` ASC),
  CONSTRAINT `fk_tblProject_tblUser1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblstyle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblstyle` (
  `styleID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`styleID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblideabookphoto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblideabookphoto` (
  `photoID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(355) NULL DEFAULT NULL,
  `tilte` VARCHAR(145) NULL DEFAULT NULL,
  `description` VARCHAR(555) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `projectID` INT(10) UNSIGNED NOT NULL,
  `categoryID` INT(10) UNSIGNED NOT NULL,
  `styleID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`photoID`),
  INDEX `fk_tblIdeaBookPhoto_tblProject1_idx` (`projectID` ASC),
  INDEX `fk_tblIdeaBookPhoto_tblCategory1_idx` (`categoryID` ASC),
  INDEX `fk_tblIdeaBookPhoto_tblStyle1_idx` (`styleID` ASC),
  CONSTRAINT `fk_tblIdeaBookPhoto_tblCategory1`
    FOREIGN KEY (`categoryID`)
    REFERENCES `mydb`.`tblcategory` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblIdeaBookPhoto_tblProject1`
    FOREIGN KEY (`projectID`)
    REFERENCES `mydb`.`tblproject` (`projectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblIdeaBookPhoto_tblStyle1`
    FOREIGN KEY (`styleID`)
    REFERENCES `mydb`.`tblstyle` (`styleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblideabookphoto_has_tblideabook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblideabookphoto_has_tblideabook` (
  `tblIdeaBookPhoto_photoID` INT(10) UNSIGNED NOT NULL,
  `tblIdeaBook_ideaBookID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`tblIdeaBookPhoto_photoID`, `tblIdeaBook_ideaBookID`),
  INDEX `fk_tblIdeaBookPhoto_has_tblIdeaBook_tblIdeaBook1_idx` (`tblIdeaBook_ideaBookID` ASC),
  INDEX `fk_tblIdeaBookPhoto_has_tblIdeaBook_tblIdeaBookPhoto1_idx` (`tblIdeaBookPhoto_photoID` ASC),
  CONSTRAINT `fk_tblIdeaBookPhoto_has_tblIdeaBook_tblIdeaBook1`
    FOREIGN KEY (`tblIdeaBook_ideaBookID`)
    REFERENCES `mydb`.`tblideabook` (`ideaBookID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblIdeaBookPhoto_has_tblIdeaBook_tblIdeaBookPhoto1`
    FOREIGN KEY (`tblIdeaBookPhoto_photoID`)
    REFERENCES `mydb`.`tblideabookphoto` (`photoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblorder` (
  `orderID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `createdTime` DATETIME NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`orderID`),
  INDEX `fk_tblOrder_tblUser1_idx` (`userID` ASC),
  CONSTRAINT `fk_tblOrder_tblUser1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblorderdetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblorderdetail` (
  `orderDetailID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `productID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `quantity` INT(10) UNSIGNED NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `storeID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `orderID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`orderDetailID`),
  INDEX `fk_tblOrderDetail_tblOrder_idx` (`orderID` ASC),
  CONSTRAINT `fk_tblOrderDetail_tblOrder`
    FOREIGN KEY (`orderID`)
    REFERENCES `mydb`.`tblorder` (`orderID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tbltypeofwork`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbltypeofwork` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(85) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblprofessional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblprofessional` (
  `nameProfessional` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  `tblTypeOfWork_id` INT(11) NOT NULL,
  PRIMARY KEY (`userID`),
  INDEX `fk_tblProfessional_tblUser2_idx` (`userID` ASC),
  INDEX `fk_tblProfessional_tblTypeOfWork1_idx` (`tblTypeOfWork_id` ASC),
  CONSTRAINT `fk_tblProfessional_tblTypeOfWork1`
    FOREIGN KEY (`tblTypeOfWork_id`)
    REFERENCES `mydb`.`tbltypeofwork` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblProfessional_tblUser2`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblseller`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblseller` (
  `taxNumber` VARCHAR(45) NULL DEFAULT NULL,
  `storeAddress` VARCHAR(245) NULL DEFAULT NULL,
  `sellerName` VARCHAR(245) NULL DEFAULT NULL,
  `startDate` DATE NULL DEFAULT NULL,
  `dueDate` DATE NULL DEFAULT NULL,
  `phone` INT(11) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`userID`),
  INDEX `fk_tblSeller_tblProfessional1_idx` (`userID` ASC),
  CONSTRAINT `fk_tblSeller_tblProfessional1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tblprofessional` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblproduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblproduct` (
  `productID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(145) NULL DEFAULT NULL,
  `barCode` VARCHAR(45) NULL DEFAULT NULL,
  `descripsion` VARCHAR(545) NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `size` VARCHAR(45) NULL DEFAULT NULL,
  `material` VARCHAR(45) NULL DEFAULT NULL,
  `warranty` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `tblSeller_userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`productID`),
  INDEX `fk_tblProduct_tblSeller1_idx` (`tblSeller_userID` ASC),
  CONSTRAINT `fk_tblProduct_tblSeller1`
    FOREIGN KEY (`tblSeller_userID`)
    REFERENCES `mydb`.`tblseller` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblpromotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblpromotion` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(145) NULL DEFAULT NULL,
  `description` VARCHAR(545) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `startDate` DATE NULL DEFAULT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblproduct_has_tblpromotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblproduct_has_tblpromotion` (
  `tblProduct_productID` INT(10) UNSIGNED NOT NULL,
  `tblPromotion_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`tblProduct_productID`, `tblPromotion_id`),
  INDEX `fk_tblProduct_has_tblPromotion_tblPromotion1_idx` (`tblPromotion_id` ASC),
  INDEX `fk_tblProduct_has_tblPromotion_tblProduct1_idx` (`tblProduct_productID` ASC),
  CONSTRAINT `fk_tblProduct_has_tblPromotion_tblProduct1`
    FOREIGN KEY (`tblProduct_productID`)
    REFERENCES `mydb`.`tblproduct` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblProduct_has_tblPromotion_tblPromotion1`
    FOREIGN KEY (`tblPromotion_id`)
    REFERENCES `mydb`.`tblpromotion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblproductphoto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblproductphoto` (
  `productPhotoID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(245) NULL DEFAULT NULL,
  `title` VARCHAR(115) NULL DEFAULT NULL,
  `description` VARCHAR(545) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `tblStyle_styleID` INT(10) UNSIGNED NOT NULL,
  `tblCategory_categoryID` INT(10) UNSIGNED NOT NULL,
  `tblProduct_productID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`productPhotoID`),
  INDEX `fk_tblProductPhoto_tblStyle1_idx` (`tblStyle_styleID` ASC),
  INDEX `fk_tblProductPhoto_tblCategory1_idx` (`tblCategory_categoryID` ASC),
  INDEX `fk_tblProductPhoto_tblProduct1_idx` (`tblProduct_productID` ASC),
  CONSTRAINT `fk_tblProductPhoto_tblCategory1`
    FOREIGN KEY (`tblCategory_categoryID`)
    REFERENCES `mydb`.`tblcategory` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblProductPhoto_tblProduct1`
    FOREIGN KEY (`tblProduct_productID`)
    REFERENCES `mydb`.`tblproduct` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblProductPhoto_tblStyle1`
    FOREIGN KEY (`tblStyle_styleID`)
    REFERENCES `mydb`.`tblstyle` (`styleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tblstory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tblstory` (
  `storyID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(85) NULL DEFAULT NULL,
  `content` VARCHAR(545) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`storyID`),
  INDEX `fk_tblStory_tblUser1_idx` (`userID` ASC),
  CONSTRAINT `fk_tblStory_tblUser1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`tbltracking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tbltracking` (
  `count` INT(11) NULL DEFAULT NULL,
  `lastUpdate` DATETIME NULL DEFAULT NULL,
  `userID` INT(10) UNSIGNED NOT NULL,
  `categoryID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`userID`, `categoryID`),
  INDEX `fk_tblTracking_tblUser1_idx` (`userID` ASC),
  INDEX `fk_tblTracking_tblCategory1_idx` (`categoryID` ASC),
  CONSTRAINT `fk_tblTracking_tblCategory1`
    FOREIGN KEY (`categoryID`)
    REFERENCES `mydb`.`tblcategory` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblTracking_tblUser1`
    FOREIGN KEY (`userID`)
    REFERENCES `mydb`.`tbluser` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
