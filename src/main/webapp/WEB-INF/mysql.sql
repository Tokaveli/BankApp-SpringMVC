DROP DATABASE IF EXISTS `bank`
;

CREATE DATABASE `bank`
;

USE `bank`
;

SET FOREIGN_KEY_CHECKS=0 
;

/* Drop Tables */

DROP TABLE IF EXISTS `accounts` CASCADE
;

DROP TABLE IF EXISTS `addresses` CASCADE
;

DROP TABLE IF EXISTS `debit_cards` CASCADE
;

DROP TABLE IF EXISTS `deposits` CASCADE
;

DROP TABLE IF EXISTS `errands` CASCADE
;

DROP TABLE IF EXISTS `loans` CASCADE
;

DROP TABLE IF EXISTS `prepaids` CASCADE
;

DROP TABLE IF EXISTS `transfers` CASCADE
;

DROP TABLE IF EXISTS `users` CASCADE
;

/* Create Tables */

CREATE TABLE `accounts`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(100) NOT NULL,
	`password_hash` TEXT NOT NULL,
	`account_number` VARCHAR(100) NOT NULL,
	`balance` DECIMAL(10,2) NOT NULL,
	CONSTRAINT `PK_accounts` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `addresses`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`street` VARCHAR(100) NOT NULL,
	`house_number` VARCHAR(100) NOT NULL,
	`city` VARCHAR(100) NOT NULL,
	`zip_code` VARCHAR(100) NOT NULL,
	CONSTRAINT `PK_addresses` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `debit_cards`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`card_number` VARCHAR(100) NOT NULL,
	`pin` VARCHAR(100) NOT NULL,
	`cvv` VARCHAR(100) NOT NULL,
	`expiry_date` DATE NOT NULL,
	CONSTRAINT `PK_debit_cards` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `deposits`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`period` INT NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`open_date` DATE NOT NULL,
	`close_date` DATE NOT NULL,
	CONSTRAINT `PK_deposits` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `errands`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`receiver_name` VARCHAR(100) NOT NULL,
	`receiver_address` VARCHAR(100) NOT NULL,
	`account_number` VARCHAR(100) NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`title` VARCHAR(100) NOT NULL,
	`send_date` DATE NOT NULL,
	`period` INT NOT NULL,
	CONSTRAINT `PK_errands` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `loans`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`period` INT NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`open_date` DATE NOT NULL,
	`close_date` DATE NOT NULL,
	CONSTRAINT `PK_loans` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `prepaids`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`phone_number` VARCHAR(100) NOT NULL,
	`phone_operator` VARCHAR(100) NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`send_date` DATE NOT NULL,
	CONSTRAINT `PK_transfers` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `transfers`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`receiver_name` VARCHAR(100) NOT NULL,
	`receiver_address` VARCHAR(100) NOT NULL,
	`account_number` VARCHAR(100) NOT NULL,
	`amount` DECIMAL(10,2) NOT NULL,
	`title` VARCHAR(100) NOT NULL,
	`send_date` DATE NOT NULL,
	CONSTRAINT `PK_transfers` PRIMARY KEY (`id` ASC)
)

;

CREATE TABLE `users`
(
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_account` INT NOT NULL,
	`id_address` INT NOT NULL,
	`firstname` VARCHAR(100) NOT NULL,
	`lastname` VARCHAR(100) NOT NULL,
	`pin` VARCHAR(100) NOT NULL,
	`series_number` VARCHAR(100) NOT NULL,
	`expiry_date` DATE NOT NULL,
	`birth_date` DATE NOT NULL,
	`birthplace` VARCHAR(100) NOT NULL,
	`mothers_maiden_name` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`phone_number` VARCHAR(100) NOT NULL,
	CONSTRAINT `PK_users` PRIMARY KEY (`id` ASC)
)

;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE `debit_cards` 
 ADD INDEX `IXFK_debit_cards_accounts` (`id_account` ASC)
;

ALTER TABLE `deposits` 
 ADD INDEX `IXFK_deposits_accounts` (`id_account` ASC)
;

ALTER TABLE `errands` 
 ADD INDEX `IXFK_errands_accounts` (`id_account` ASC)
;

ALTER TABLE `loans` 
 ADD INDEX `IXFK_loans_accounts` (`id_account` ASC)
;

ALTER TABLE `prepaids` 
 ADD INDEX `IXFK_prepaids_accounts` (`id_account` ASC)
;

ALTER TABLE `transfers` 
 ADD INDEX `IXFK_transfers_accounts` (`id_account` ASC)
;

ALTER TABLE `users` 
 ADD INDEX `IXFK_users_accounts` (`id_account` ASC)
;

ALTER TABLE `users` 
 ADD INDEX `IXFK_users_addresses` (`id_address` ASC)
;

/* Create Foreign Key Constraints */

ALTER TABLE `debit_cards` 
 ADD CONSTRAINT `FK_debit_cards_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `deposits` 
 ADD CONSTRAINT `FK_deposits_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `errands` 
 ADD CONSTRAINT `FK_errands_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `loans` 
 ADD CONSTRAINT `FK_loans_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `prepaids` 
 ADD CONSTRAINT `FK_prepaids_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `transfers` 
 ADD CONSTRAINT `FK_transfers_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `users` 
 ADD CONSTRAINT `FK_users_accounts`
	FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `users` 
 ADD CONSTRAINT `FK_users_addresses`
	FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1 
;
