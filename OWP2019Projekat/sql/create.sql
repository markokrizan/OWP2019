-- MySQL Script generated by MySQL Workbench
-- Tue Dec  4 19:20:12 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Airline` DEFAULT CHARACTER SET utf8 ;
USE `Airline` ;

-- -----------------------------------------------------
-- Table `Airline`.`Airport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Airline`.`AIRPORT` ;

CREATE TABLE IF NOT EXISTS `Airline`.`AIRPORT` (
  `airport_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `deleted` TINYINT NOT NULL,
  PRIMARY KEY (`airport_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`Flight`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Airline`.`FLIGHT` ;

CREATE TABLE IF NOT EXISTS `Airline`.`FLIGHT` (
  `flight_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(100) NOT NULL UNIQUE,
  `departure_date` DATETIME NOT NULL,
  `arrival_date` DATETIME NOT NULL,
  `departure_airport_id` INT NOT NULL,
  `arrival_airport_id` INT NOT NULL,
  `no_of_seats` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `deleted` TINYINT NOT NULL,
  PRIMARY KEY (`flight_id`),
  CONSTRAINT `departure_airport_id`
    FOREIGN KEY (`departure_airport_id`)
    REFERENCES `Airline`.`Airport` (`airport_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `arrival_airport_id`
    FOREIGN KEY (`arrival_airport_id`)
    REFERENCES `Airline`.`Airport` (`airport_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Airline`.`USER` ;

CREATE TABLE IF NOT EXISTS `Airline`.`USER` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `registration_date` DATE NOT NULL,
  `role` ENUM('REGULAR', 'ADMIN') NOT NULL,
  `blocked` TINYINT NOT NULL,
  `deleted` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Airline`.`TICKET` ;

CREATE TABLE IF NOT EXISTS `Airline`.`TICKET` (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `departure_flight_id` INT NOT NULL,
  `arrival_flight_id` INT NULL,
  `departure_flight_seat_no` INT NOT NULL,
  `arrival_flight_seat_no` INT NULL,
  `reservation_date` DATETIME NULL,
  `sale_date` DATETIME NULL,
  `user_id` INT NULL,
  `deleted` TINYINT NOT NULL,
  PRIMARY KEY (`ticket_id`),
  CONSTRAINT `departure_flight_id`
    FOREIGN KEY (`departure_flight_id`)
    REFERENCES `Airline`.`Flight` (`flight_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `arrival_flight_id`
    FOREIGN KEY (`arrival_flight_id`)
    REFERENCES `Airline`.`Flight` (`flight_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `Airline`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- ----------------------------------------------------

-- REPORT VIEWS:

CREATE VIEW report_specific_by_airport_all_time
AS
	SELECT a.airport_id, a.name, flight_id, count(ticket_id) as no_of_sold_tickets, price * count(ticket_id) AS total_revenue
	FROM 
		airport a join flight f on a.airport_id = f.departure_airport_id or a.airport_id = f.arrival_airport_id
			left outer join ticket t on (f.flight_id = t.departure_flight_id or f.flight_id = t.arrival_flight_id) and t.sale_date IS NOT NULL
	GROUP BY a.airport_id, a.name, f.price, f.flight_id;
    
-- ----------------------------------------------------

CREATE VIEW total_by_airport_all_time
AS
	SELECT name, count(flight_id) as no_of_flights, sum(no_of_sold_tickets) as total_tickets_sold, sum(total_revenue) as total_revenue 
		FROM report_specific_by_airport_all_time
	GROUP BY airport_id, name;

-- ----------------------------------------------------

CREATE VIEW total_all_time
AS
	SELECT sum(no_of_flights) AS total_flights, sum(total_tickets_sold) as total_tickets, sum(total_revenue) as total
	FROM total_by_airport_all_time;
	
-- ----------------------------------------------------

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
