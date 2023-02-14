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
  `last_active` DATE NULL,
  `role` VARCHAR(45) NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project` ;

CREATE TABLE IF NOT EXISTS `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `date_posted` DATE NULL,
  `description` TEXT NULL,
  `active_status` TINYINT NULL,
  `user_id` INT NOT NULL,
  `image_primary` VARCHAR(2500) NULL,
  `start_date` DATE NULL,
  `projected_date` DATE NULL,
  `enabled` TINYINT NULL,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `role`, `address_id`) VALUES (5, 'leo', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Leonardo', 'Hamato', 1, 'leo@tnmt.com', 1, 'Happy go lucky electrician ', 'https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/teenage-mutant-ninja-turtles/characters/leonardo-character-web-desktop.png?height=0&width=480&matte=true&crop=false', '2023-01-01', '2023-01-01', NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `role`, `address_id`) VALUES (2, 'mic', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Michelangelo', 'Hamato', 1, 'mic@tnmt.com', 1, 'Art, literature, language', 'https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/teenage-mutant-ninja-turtles/characters/michelangelo-character-web-desktop.png?height=0&width=480&matte=true&crop=false', '2023-01-01', '2023-01-01', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `role`, `address_id`) VALUES (3, 'don', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Donatello', 'Hamato', 1, 'don@tnmt.com', 1, 'Tech wiz and general genius. Responsible for most technology and machinery. ', 'https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/teenage-mutant-ninja-turtles/characters/donatello-character-web-desktop.png?height=0&width=480&matte=true&crop=false', '2023-01-01', '2023-01-01', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `role`, `address_id`) VALUES (4, 'rap', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'Raphael', 'Hamato', 1, 'rap@tnmt.com', 1, 'Happy to exchange workout tips for bread recipes', 'https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/teenage-mutant-ninja-turtles/characters/raphael-character-web-desktop.png?height=0&width=480&matte=true&crop=false', '2023-01-01', '2023-01-01', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `availability`, `email`, `enabled`, `bio`, `profile_image`, `created_date`, `last_active`, `role`, `address_id`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'admin', 'admin', 1, 'admin@admin.com', 1, 'admin', NULL, '2023-02-07', '2023-02-07', 'ADMIN', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Woodworking', 'basic power tool uses, sanding, painting', 'https://www.familyhandyman.com/wp-content/uploads/2022/02/10-Woodworking-Portable-Power-Tools-e1645736309828.jpg');
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Cooking', 'whip up a delightful pastry ', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (3, 'Computing', 'JAVA, OOP expert', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (4 , 'Electrician', 'can walk you through basic electric installs ', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (5, 'Sewing', 'Repair and darn back to new', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (6, 'Painting', 'Expert interior and exterior painter', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (7, 'Dog training', 'Training basics', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (8, 'Home construction', 'home building from foundation to roof', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (9, 'Mechanic', 'Fix cars, trucks, and RVs', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (10, 'Gardening', 'homesteading officianado ', NULL);
INSERT INTO `skill` (`id`, `name`, `description`, `image_url`) VALUES (11, 'Tutoring', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project`
-- -----------------------------------------------------
START TRANSACTION;
USE `skilldb`;
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (1, 'Help with Java programming', '2023-02-07', 'I am struggling really hard this semester with programming. Willing to pay by the hour. Name your rate!', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497235624153128/pexels-christina-morillo-1181298.jpg', '2023-02-01', '2023-03-01', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (2, 'Need help with geometry ', '2023-02-07', 'Geometry is having its way with my 10th grader and his finals are right around the corner. Anyone with a background in geometry and has teaching experience please contact me ASAP!', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497374589829120/pexels-max-fischer-5212338.jpg', '2023-02-01', '2023-04-01', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (3, 'Changing my transmission', '2023-02-10', 'My transmission finally went out, looking for soe help repairing it! I know the basics about cars but this is a bit out of my wheel house. ', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497407062122597/pexels-sergey-korolev-14456303.jpg', '2023-02-10', '2023-04-05', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (4, 'Seeking Culinary Artist', '2023-01-20', 'I\'m tired of eating the same old food. Looking for someone with culinary expertise to share some tips and maybe help me meal plan.', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497406000971866/pexels-rene-asmussen-2544829.jpg', '2023-02-05', '2023-04-02', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (5, 'Broke my window, need it fixed. ', '2023-02-03', 'The neighborhood kids are at it again. This is the third window this year! Please help an old man out.', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497406487515156/pexels-sadi-gokpnar-14252446.jpg', '2023-02-03', '2023-02-17', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (6, 'New Home, no paint.', '2023-02-05', 'The house is beautiful from the outside, but it could definitely use a splash of color on the interior. Help wanted!', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497444735365190/pexels-tima-miroshnichenko-6474471.jpg', '2023-02-01', '2023-03-01', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (7, 'These A/C Units Have To Go', '2023-02-10', 'Have a mountain of A/C units that need to be out of here by the weekend. Can help load them! ', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497475731271710/pexels-tom-fisk-11256510.jpg', '2023-02-10', '2023-02-20', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (8, 'Electrical help needed', '2023-02-09', 'I keep having issues throwing a breaker, need help figuring that out!', 1, 2, 'https://media.discordapp.net/attachments/1072258955679633449/1074497272290738197/pexels-emmanuel-ikwuegbu-8005397.jpg?width=1510&height=1008', '2023-02-11', '2023-02-28', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (9, 'Dog walking/training', '2023-02-08', 'Looking for someone who is good with dogs to help me train my sweet little monsters. ', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497234579751002/pexels-blue-bird-7210535.jpg', '2023-02-13', '2023-02-26', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (10, 'Seamstress Wanted', '2023-02-06', 'Tore a hole in my shirt while jumping over a chainlink fence. I blame the neighbor for not locking up their mean old dog!', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497235221487686/pexels-brian-magill-13005863.jpg', '2023-02-10', '2023-03-01', 1);
INSERT INTO `project` (`id`, `name`, `date_posted`, `description`, `active_status`, `user_id`, `image_primary`, `start_date`, `projected_date`, `enabled`) VALUES (11, 'Gardening support', '2023-02-10', 'Wanting to prep a large garden, need some hep.', 1, 2, 'https://cdn.discordapp.com/attachments/1072258955679633449/1074497375856504902/pexels-quang-nguyen-vinh-2134798.jpg', '2023-02-10', '2023-05-15', 1);

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
INSERT INTO `project_image` (`id`, `image_url`, `project_id`, `caption`, `created_date`) VALUES (1, 'https://ih1.redbubble.net/image.4703866326.0628/poster', 1, 'caption', '2023-02-07');
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
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (1, 1, 1, 'this is a description');
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (2, 2, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (3, 3, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (4, 7, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (5, 8, NULL, NULL);
INSERT INTO `user_skill` (`user_id`, `skill_id`, `experience_level_id`, `description`) VALUES (2, 5, NULL, NULL);

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
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (1, 3);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (2, 11);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (3, 9);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (4, 2);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (5, 8);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (6, 6);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (7, 8);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (8, 4);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (9, 7);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (10, 5);
INSERT INTO `project_has_skill` (`project_id`, `skill_id`) VALUES (11, 10);

COMMIT;

