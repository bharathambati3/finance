SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE `fd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(32) NOT NULL,
  `amount` double NOT NULL,
  `deposited_on` date NOT NULL,
  `maturity_on` date NOT NULL,
  `interest_rate` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `mf_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `mf_investment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `scheme_id` int(11) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `purchased_on` datetime NOT NULL,
  `invested_on` datetime DEFAULT NULL,
  `amount` decimal(15,2) NOT NULL,
  `purchase_nav` decimal(10,4) DEFAULT NULL,
  `investment_type` enum('LUMPSUM','SIP') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `scheme_id` (`scheme_id`),
  KEY `investment_type` (`investment_type`),
  KEY `purchased_on` (`purchased_on`),
  KEY `invested_on` (`invested_on`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `mf_investment_ibfk_1` FOREIGN KEY (`scheme_id`) REFERENCES `mf_schemes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `mf_schemes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `fund_type` enum('LARGE_CAP','MID_CAP','SMALL_CAP','MULTI_CAP','HYBRID','ELSS') NOT NULL,
  `is_tax_saving` tinyint(1) DEFAULT '0',
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `current_nav` decimal(10,4) DEFAULT NULL,
  `latest_nav_date` datetime DEFAULT NULL,
  `amfi_code` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `name` (`name`),
  KEY `fund_type` (`fund_type`),
  KEY `amfi_code` (`amfi_code`),
  KEY `latest_nav_date` (`latest_nav_date`),
  CONSTRAINT `mf_schemes_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `mf_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `mf_scheme_nav_tracker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheme_id` int(11) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `nav_date` date NOT NULL,
  `nav` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `scheme_nav_date` (`scheme_id`,`nav_date`,`nav`),
  KEY `scheme_id_idx` (`scheme_id`),
  KEY `nav_date_idx` (`nav_date`),
  CONSTRAINT `mf_scheme_nav_tracker_ibfk_1` FOREIGN KEY (`scheme_id`) REFERENCES `mf_schemes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `mf_sip_reg_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `scheme_id` int(11) NOT NULL,
  `created_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `registered_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `recurring_type` enum('MONTHLY','QUARTERLY') NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `scheme_id` (`scheme_id`),
  KEY `end_date` (`end_date`),
  KEY `start_date` (`start_date`),
  KEY `amount` (`amount`),
  KEY `registered_on` (`registered_on`),
  CONSTRAINT `mf_sip_reg_info_ibfk_1` FOREIGN KEY (`scheme_id`) REFERENCES `mf_schemes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `nsc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(32) NOT NULL,
  `amount` double NOT NULL,
  `deposited_on` date NOT NULL,
  `maturity_on` date NOT NULL,
  `interest_rate` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `ppf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(32) NOT NULL,
  `amount` double NOT NULL,
  `deposited_on` date NOT NULL,
  `maturity_on` date NOT NULL,
  `interest_rate` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- 2018-12-31 12:42:47