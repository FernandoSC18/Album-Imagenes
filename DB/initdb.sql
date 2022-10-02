
/*THIS SQL FILE IS EXECUTED WHEN DATABASE IS INITIALIZED, 
* THAT IS WHAT : 
    HERE NOT CREATE A DATABASE
    HERE NOT SELECT A DATABASE
* IF FOR SOMEWAY AN ERROR HAPPENDS VERYFY THE DATABASE NAME: (DB_DATABASE IN .ENV FILE FROM ROOT PROYECT)
* THEN RUN IT SCRIPT 
*/

SET NAMES utf8;
SET character_set_client = utf8mb4;

/*
* TABLE FILES : Storage files and allow have a order and control of type files
*/
CREATE TABLE IF NOT EXISTS `files` (
  `file_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key of \"files\" table',
  `file_name` VARCHAR(50) NOT NULL COMMENT 'Name of one file',
  `file_description` VARCHAR(500) NULL COMMENT 'Description of file',
  `file_type` VARCHAR(8) NULL COMMENT 'File type or extension file example (pdf, csv, png, html, etc)',
  `file_mime_type` VARCHAR(30) NULL COMMENT 'Identifier format file, examples (text/plan, text/html, text/csv, image/jpeg, appliaction/json, etc)',
  `file_size` INT NULL COMMENT 'Size of file in bytes',
  `file_data` LONGTEXT NOT NULL COMMENT 'Binary data of file convert and storage in Base64, it is the file',
  `created_date` DATETIME NULL COMMENT 'Date of created file record for order files',
  `updated_date` DATETIME NULL COMMENT 'Date of updated file record for order files',
  PRIMARY KEY (`file_id`),
  UNIQUE INDEX `file_id_UNIQUE` (`file_id` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Storage files and allow have a order and control of type files';

/*
* TABLE ALBUM : Allow group images whit an album record
*/
CREATE TABLE IF NOT EXISTS `album` (
  `album_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Primary key of \"album\" table',
  `album_name` VARCHAR(50) NOT NULL COMMENT 'name of álbum',
  `album_description` VARCHAR(500) NULL COMMENT 'description of album',
  `created_date` DATETIME NULL COMMENT 'Date of created record for control and database search',
  `updated_date` DATETIME NULL COMMENT 'Date of updated record for control and database search',
  PRIMARY KEY (`album_id`),
  UNIQUE INDEX `id_album_UNIQUE` (`album_id` ASC) VISIBLE,
  UNIQUE INDEX `album_name_UNIQUE` (`album_name` ASC) VISIBLE)
ENGINE = InnoDB
COMMENT = 'Allow group images whit an album record';
 
/*
* TABLE IMAGE : Record for a individual image whit all reference to their album or image file.
*/
CREATE TABLE IF NOT EXISTS `image` (
  `image_id` BIGINT(15) NOT NULL AUTO_INCREMENT COMMENT 'Primary key of \"image\" table',
  `image_name` VARCHAR(50) NOT NULL COMMENT 'name of image',
  `image_description` VARCHAR(500) NULL COMMENT 'description of image',
  `created_date` DATETIME NULL COMMENT 'Date of created record for control and database search',
  `updated_date` DATETIME NULL COMMENT 'Date of updated record for control and database search',
  `album_id` INT NOT NULL COMMENT 'foreign key ID from \"album\" table, this associate one album to this image record',
  `file_id` BIGINT NULL COMMENT 'foreign key ID from \"files\" table, this associate one file as image to this image record',
  PRIMARY KEY (`image_id`),
  UNIQUE INDEX `image_id_UNIQUE` (`image_id` ASC) VISIBLE,
  INDEX `fk_file_id_idx` (`file_id` ASC) VISIBLE,
  CONSTRAINT `fk_album_id`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`album_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_file_id`
    FOREIGN KEY (`file_id`)
    REFERENCES `files` (`file_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Record for a individual image whit all reference to their album or image file.';
