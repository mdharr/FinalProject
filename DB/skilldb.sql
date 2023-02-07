-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema skilldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `skilldb` ;

-- -----------------------------------------------------
-- Schema skilldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `skilldb` DEFAULT CHARACTER SET utf8 ;
USE `skilldb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `availability` TINYINT NULL,
  `email` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `bio` TEXT NULL,
  `profile_image` VARCHAR(2500) NULL,
  `created_date` DATETIME NULL,
  `last_active` DATETIME NULL,
  `address_id` INT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill` ;

CREATE TABLE IF NOT EXISTS `skill` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project` ;

CREATE TABLE IF NOT EXISTS `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `date_posted` DATETIME NULL,
  `description` TEXT NULL,
  `active_status` TINYINT NULL,
  `user_id` INT NOT NULL,
  `image_primary` VARCHAR(2500) NULL,
  `start_date` DATETIME NULL,
  `projected_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_project_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL,
  `date_posted` DATETIME NULL,
  `comment` TEXT NULL,
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_project1_idx` (`project_id` ASC),
  INDEX `fk_comment_User1_idx` (`user_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_comment_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_image` ;

CREATE TABLE IF NOT EXISTS `project_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(2500) NULL,
  `project_id` INT NOT NULL,
  `caption` VARCHAR(2500) NULL,
  `created_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_image_projects1_idx` (`project_id` ASC),
  CONSTRAINT `fk_project_image_projects1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `experience_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `experience_level` ;

CREATE TABLE IF NOT EXISTS `experience_level` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_skill` ;

CREATE TABLE IF NOT EXISTS `user_skill` (
  `user_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `experience_level_id` INT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`user_id`, `skill_id`),
  INDEX `fk_user_has_skills_skills1_idx` (`skill_id` ASC),
  INDEX `fk_user_has_skills_user1_idx` (`user_id` ASC),
  INDEX `fk_user_has_skill_experience_level1_idx` (`experience_level_id` ASC),
  CONSTRAINT `fk_user_has_skills_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_skills_skills1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_skill_experience_level1`
    FOREIGN KEY (`experience_level_id`)
    REFERENCES `experience_level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `followed_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `followed_user` ;

CREATE TABLE IF NOT EXISTS `followed_user` (
  `user_id` INT NOT NULL,
  `followed_user_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `followed_user_id`),
  INDEX `fk_user_has_user_user2_idx` (`followed_user_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`followed_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_member` ;

CREATE TABLE IF NOT EXISTS `project_member` (
  `user_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `comments` TEXT NULL,
  `rating` INT NULL,
  `rating_comments` TEXT NULL,
  PRIMARY KEY (`user_id`, `project_id`),
  INDEX `fk_user_has_project_project1_idx` (`project_id` ASC),
  INDEX `fk_user_has_project_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_project_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_has_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_has_skill` ;

CREATE TABLE IF NOT EXISTS `project_has_skill` (
  `project_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `skill_id`),
  INDEX `fk_project_has_skill_skill1_idx` (`skill_id` ASC),
  INDEX `fk_project_has_skill_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_project_has_skill_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_has_skill_skill1`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS skillswap@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'skillswap'@'localhost' IDENTIFIED BY 'skillswap';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'skillswap'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, 'sewer', 'New York City', 'NY', '12345');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `address_id`, `role`) VALUES (5, 'leo', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Leonardo', 'Hamato', 0, 'leo@tnmt.com', NULL, 'Happy go lucky electrician ', 'https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/teenage-mutant-ninja-turtles/characters/leonardo-character-web-desktop.png?height=0&width=480&matte=true&crop=false', '2022-01-01', '2022-02-01', 1, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `address_id`, `role`) VALUES (2, 'mic', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Michelangelo', 'Hamato', 0, 'mic@tnmt.com', NULL, 'I know lots of things', 'https://static.wikia.nocookie.net/tmnt2012series/images/8/88/2012_Michelangelo_clean_character_image.png/revision/latest?cb=20130809041043', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `address_id`, `role`) VALUES (3, 'don', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Donatello', 'Hamato', 0, 'don@tnmt.com', NULL, 'Looking for some comedy practice', 'https://static.wikia.nocookie.net/tmnt2012series/images/d/d6/Donnyboy.png/revision/latest?cb=20170428224932', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `address_id`, `role`) VALUES (4, 'rap', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Raphael', 'Hamato', 0, 'rap@tnmt.com', NULL, 'Happy to exchange workout tips for bread recipes', 'https://static.wikia.nocookie.net/tmnt2012series/images/6/63/Raph-rage.png/revision/latest?cb=20170428232825', NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `address_id`, `role`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 'ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (1, 'woodworking', 'basic power tool uses, sanding, painting', 'https://www.familyhandyman.com/wp-content/uploads/2022/02/10-Woodworking-Portable-Power-Tools-e1645736309828.jpg');
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (2, 'cooking', 'whip up a delightful pastry ', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (3, 'computing', 'JAVA, OOP expert', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (4 , 'electrician', 'can walk you through basic electric installs ', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (5, 'sewing', 'Repair and darn back to new', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`) VALUES (1, 'Repair a tear in my mask ', '2023-02-07', 'Mask has a rip and I need to to fight crime! ', 0, 1, NULL, '2022-02-01', '2022-03-01');
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`) VALUES (2, 'Want to get better at italian', '2023-02-07', 'Looking for help with my italian , can offer cupckae making tips', 1, 2, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `comment` (`id`, `date_posted`, `comment`, `project_id`, `user_id`, `in_reply_to_id`) VALUES (1, '2023-02-08', 'Thats really cool that you got lessons! ', 2, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `project_image` (`id`, `image_url`, `project_id`, `caption`, `created_date`) VALUES (1, NULL, 1, NULL, NULL);
INSERT INTO `project_image` (`id`, `image_url`, `project_id`, `caption`, `created_date`) VALUES (2, 'https://ih1.redbubble.net/image.4703866326.0628/poster,504x498,f8f8f8-pad,600x600,f8f8f8.jpg', 2, 'caption', '2023-02-07');

COMMIT;


-- -----------------------------------------------------
-- Data for table `experience_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `experience_level` (`id`, `name`) VALUES (1, 'novice');
INSERT INTO `experience_level` (`id`, `name`) VALUES (2, 'advanced beginner');
INSERT INTO `experience_level` (`id`, `name`) VALUES (3, 'competent');
INSERT INTO `experience_level` (`id`, `name`) VALUES (4, 'proficient');
INSERT INTO `experience_level` (`id`, `name`) VALUES (5, 'expert');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (1, 4, 1, 'this is a description');

COMMIT;


-- -----------------------------------------------------
-- Data for table `followed_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `followed_user` (`user_id`, `followed_user_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `project_member` (`user_id`, `project_id`, `comments`, `rating`, `rating_comments`) VALUES (1, 1, 'Our first project', 10, 'Great stuff');

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_has_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (1, 1);

COMMIT;

