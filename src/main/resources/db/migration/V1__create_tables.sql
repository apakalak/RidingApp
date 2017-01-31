CREATE TABLE `cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `day_charge` INT NOT NULL,
  `night_charge` INT NOT NULL,
  `lux_tax` DECIMAL NOT NULL,
  `created`    TIMESTAMP      NOT NULL  DEFAULT NOW(),
  `modified`   TIMESTAMP      NOT NULL  DEFAULT NOW(),
  PRIMARY KEY (`id`));

CREATE TABLE `drivers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('MALE', 'FEMALE') NOT NULL,
  `no_of_voilation` INT NULL DEFAULT 0,
  `created`    TIMESTAMP      NOT NULL  DEFAULT NOW(),
  `modified`   TIMESTAMP      NOT NULL  DEFAULT NOW(),
  `city_id` INT            NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_city_id`
  FOREIGN KEY (`city_id`)
  REFERENCES `cities` (`id`)
);

CREATE TABLE `cars` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `make` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `type` ENUM('BASIC', 'LUX') NOT NULL,
  `created` TIMESTAMP NULL DEFAULT now(),
  `modified` TIMESTAMP NULL DEFAULT now(),
  `driver_id` INT            NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_driver_id`
  FOREIGN KEY (`driver_id`)
  REFERENCES `drivers` (`id`));

CREATE TABLE `passangers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('MALE', 'FEMALE') NOT NULL,
  `balance` DECIMAL NOT NULL,
  `created` TIMESTAMP NULL DEFAULT now(),
  `modified` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

CREATE TABLE `trips` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `duration` INT NOT NULL DEFAULT 0,
  `start_time` DATETIME NULL DEFAULT now(),
  `stop_time` DATETIME NULL DEFAULT now(),
  `trip_distance` DECIMAL NOT NULL DEFAULT 0,
  `cost` DECIMAL NOT NULL DEFAULT 0,
  `tip_percentage` DECIMAL NOT NULL DEFAULT 0,
  `total_cost` DECIMAL NOT NULL DEFAULT 0,
  `created` TIMESTAMP NULL DEFAULT now(),
  `modified` TIMESTAMP NULL DEFAULT now(),
  `car_id` INT            NOT NULL ,
  `pass_id` INT            NOT NULL ,
  `passanger_point` INT DEFAULT 0,
  `passanger_review`  VARCHAR(45) DEFAULT '',
  `driver_point` INT DEFAULT 0,
  `driver_review`  VARCHAR(45) DEFAULT '',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_car_id`
  FOREIGN KEY (`car_id`)
  REFERENCES `cars` (`id`),
  CONSTRAINT `fk_pass_id`
  FOREIGN KEY (`pass_id`)
  REFERENCES `passangers` (`id`));
