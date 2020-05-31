CREATE TABLE `hcb_shop`.`customers` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `bank_account` DOUBLE NOT NULL,
  `face_code` INT(11) NOT NULL UNIQUE,
  `crime_coef` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `hcb_shop`.`customers`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `hcb_shop`.`customers`
ADD COLUMN `active_box` TINYINT NOT NULL DEFAULT 0 AFTER `crime_coef`;



CREATE TABLE `hcb_shop`.`goods` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `weight` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `hcb_shop`.`goods`
ADD COLUMN `age_limit` INT NOT NULL DEFAULT 0 AFTER `weight`;

-- CREATE TABLE IF NOT EXISTS `face_bio` (
-- `id` int(11) NOT NULL,
--   `code` int(10) NOT NULL,
--   `first_name` varchar(30) NOT NULL,
--   `last_name` varchar(20) NOT NULL,
--   `reg` int(10) NOT NULL,
--   `age` int(10) NOT NULL,
--   `section` varchar(20) NOT NULL
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--
--
-- ALTER TABLE `face_bio`
--  ADD PRIMARY KEY (`id`);