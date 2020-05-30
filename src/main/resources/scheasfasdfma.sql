SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE IF NOT EXISTS `country` (
                                                `country_id` INT NOT NULL AUTO_INCREMENT,
                                                `country_name` VARCHAR(60) NULL,
                                                `country_code` VARCHAR(3) NULL,
                                                PRIMARY KEY (`country_id`));


CREATE TABLE IF NOT EXISTS `location` (
                                                 `location_id` INT NOT NULL AUTO_INCREMENT,
                                                 `location_name` VARCHAR(100) NOT NULL,
                                                 `latitiude` VARCHAR(30) NULL,
                                                 `longitude` VARCHAR(30) NULL,
                                                 `location_type` ENUM('SLOPE', 'FLAT') NULL,
                                                 `country_id` INT NOT NULL,
                                                 PRIMARY KEY (`location_id`, `country_id`),
                                                 FOREIGN KEY (`country_id`)
                                                         REFERENCES `country` (`country_id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `competition_class` (
                                                          `competition_class_id` INT NOT NULL AUTO_INCREMENT,
                                                          `competition_class_name` VARCHAR(20) NULL,
                                                          PRIMARY KEY (`competition_class_id`));


CREATE TABLE IF NOT EXISTS `task` (
                                             `task_id` INT NOT NULL AUTO_INCREMENT,
                                             `task_name` VARCHAR(45) NULL,
                                             `competition_class_id` INT NOT NULL,
                                             PRIMARY KEY (`task_id`, `competition_class_id`),


                                                 FOREIGN KEY (`competition_class_id`)
                                                     REFERENCES `competition_class` (`competition_class_id`)
                                                     ON DELETE NO ACTION
                                                     ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `pilot` (
                                              `pilot_id` INT NOT NULL AUTO_INCREMENT,
                                              `pilot_first_name` VARCHAR(45) NULL,
                                              `pilot_last_name` VARCHAR(45) NULL,
                                              `country_id` INT NOT NULL,
                                              `pilot_birth_date` DATE NULL,
                                              `pilot_rating` DECIMAL(4,2) NULL,
                                              `rank` DECIMAL(5,2) NULL,
                                              PRIMARY KEY (`pilot_id`, `country_id`),


                                                  FOREIGN KEY (`country_id`)
                                                      REFERENCES `country` (`country_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `plane` (
                                              `plane_id` INT NOT NULL AUTO_INCREMENT,
                                              `pilot_id` INT NOT NULL,
                                              `plane_wing_span` DECIMAL(3,2) NULL,
                                              `plane_color` VARCHAR(45) NULL,
                                              `plane_name` VARCHAR(45) NULL,
                                              PRIMARY KEY (`plane_id`, `pilot_id`),


                                                  FOREIGN KEY (`pilot_id`)
                                                      REFERENCES `pilot` (`pilot_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `event` (
                                              `event_id` INT NOT NULL AUTO_INCREMENT,
                                              `event_round_count` INT NULL,
                                              `event_name` VARCHAR(100) NULL,
                                              `location_id` INT NOT NULL,
                                              `competition_class_id` INT NOT NULL,
                                              `start_date` DATE NULL,
                                              `end_date` DATE NULL,
                                              PRIMARY KEY (`event_id`, `location_id`, `competition_class_id`),


                                                  FOREIGN KEY (`location_id`)
                                                      REFERENCES `location` (`location_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION,

                                                  FOREIGN KEY (`competition_class_id`)
                                                      REFERENCES `competition_class` (`competition_class_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `pilot_event` (
                                                    `event_id` INT NOT NULL,
                                                    `pilot_id` INT NOT NULL,
                                                    PRIMARY KEY (`event_id`, `pilot_id`),


                                                        FOREIGN KEY (`event_id`)
                                                            REFERENCES `event` (`event_id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,

                                                        FOREIGN KEY (`pilot_id`)
                                                            REFERENCES `pilot` (`pilot_id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `round` (
                                              `round_id` INT NOT NULL AUTO_INCREMENT,
                                              `event_id` INT NOT NULL,
                                              `round_number` INT NULL,
                                              `round_status` ENUM('ACTIVE', 'FINISHED') NULL,
                                              `round_begin_date` TIMESTAMP(2) NULL,
                                              `round_finish_date` TIMESTAMP(2) NULL,
                                              PRIMARY KEY (`round_id`, `event_id`),


                                                  FOREIGN KEY (`event_id`)
                                                      REFERENCES `event` (`event_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `flight` (
                                               `flight_id` INT NOT NULL AUTO_INCREMENT,
                                               `pilot_id` INT NOT NULL,
                                               `event_id` INT NOT NULL,
                                               `round_id` INT NOT NULL,
                                               `competition_class_id` INT NOT NULL,
                                               `stats_id` INT NOT NULL,
                                               PRIMARY KEY (`flight_id`, `competition_class_id`, `round_id`, `pilot_id`, `event_id`),


                                                   FOREIGN KEY (`pilot_id`)
                                                       REFERENCES `pilot` (`pilot_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,

                                                   FOREIGN KEY (`event_id`)
                                                       REFERENCES `event` (`event_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,

                                                   FOREIGN KEY (`round_id`)
                                                       REFERENCES `round` (`round_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,

                                                   FOREIGN KEY (`competition_class_id`)
                                                       REFERENCES `competition_class` (`competition_class_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `stats` (
                                              `stats_id` INT NOT NULL AUTO_INCREMENT,
                                              `flight_id` INT NOT NULL,
                                              `flight_duration` TIME NULL,
                                              `laps` INT NULL,
                                              `landing` INT NULL,
                                              `score` DECIMAL(5,2) NULL,
                                              PRIMARY KEY (`stats_id`, `flight_id`),

                                                  FOREIGN KEY (`flight_id`)
                                                      REFERENCES `flight` (`flight_id`)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION);

SET FOREIGN_KEY_CHECKS=1;