/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.22-MariaDB : Database - sac_springboot
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE heroku_7f3a937619e32d6;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id`,`authority`),
  CONSTRAINT `FKk91upmbueyim93v469wj7b2qh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT  CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `authorities` */

insert  into `authorities`(`id`,`authority`,`user_id`) values (12,'ROLE_USER',1),(2,'ROLE_ADMIN',2),(3,'ROLE_USER',2),(4,'ROLE_ADMIN',3),(8,'ROLE_USER',6),(10,'ROLE_USER',7),(11,'ROLE_USER',8);

/*Table structure for table `clients` */

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `direction` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `identification` varchar(255) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `clients` */

insert  into `clients`(`id`,`created_at`,`direction`,`email`,`foto`,`identification`,`lastname`,`name`,`phone`) values (1,NULL,'Cra 71a #79a-82','elbananoclasico@gmail.com',NULL,'23323333223','Reales','Sain','+573007343352'),(2,NULL,'fffffffff','juan@gmail.com',NULL,'23323333223','Gonzales','James','2323223222'),(3,NULL,'fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Richard','2323223222'),(4,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Janie','2323223222'),(5,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Linus','2323223222'),(6,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Johnny','2323223222'),(7,NULL,'csabcu','edgar@gmail.com',NULL,'12132132','perez','edgar','112134444'),(8,'2022-05-15','cra 45','elbicho@gmail.com',NULL,'121323','ronaldo','cristiano','2324242');

/*Table structure for table `global_config` */

DROP TABLE IF EXISTS `global_config`;

CREATE TABLE `global_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_address` varchar(255) DEFAULT NULL,
  `company_email` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_nit` varchar(255) DEFAULT NULL,
  `company_phone` varchar(255) DEFAULT NULL,
  `invoice_footer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `global_config` */

insert  into `global_config`(`id`,`company_address`,`company_email`,`company_name`,`company_nit`,`company_phone`,`invoice_footer`) values (1,'xxxxxxxxxx','company@gmail.com','xxxxx c.a','d3344f3f4f43','334344323','the footer');

/*Table structure for table `invoice_items` */

DROP TABLE IF EXISTS `invoice_items`;

CREATE TABLE `invoice_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itbis` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quatity` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3tu9gmkgshq8oeq5n0rinxeu` (`product_id`),
  KEY `FK46ae0lhu1oqs7cv91fn6y9n7w` (`invoice_id`),
  CONSTRAINT `FK46ae0lhu1oqs7cv91fn6y9n7w` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`),
  CONSTRAINT `FKs3tu9gmkgshq8oeq5n0rinxeu` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `invoice_items` */

insert  into `invoice_items`(`id`,`itbis`,`price`,`quatity`,`product_id`,`invoice_id`) values (4,5,299990,1,7,3),(5,0,14999,1,3,4),(6,0,25999,1,1,4),(7,0,14999,1,3,5),(8,0,12123,1,22,5),(9,0,25999,1,1,6),(10,0,22555,1,2,6),(11,2,37990,2,4,6),(12,0,22555,1,2,7),(13,0,1233,2,23,8),(14,2,37990,2,11,9),(15,0,22555,3,2,10),(16,0,25999,5,1,11),(17,0,22555,3,2,12);

/*Table structure for table `invoices` */

DROP TABLE IF EXISTS `invoices`;

CREATE TABLE `invoices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ioqm804urbgy986pdtwqtl0x` (`client_id`),
  KEY `FKbwr4d4vyqf2bkoetxtt8j9dx7` (`user_id`),
  CONSTRAINT `FK9ioqm804urbgy986pdtwqtl0x` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKbwr4d4vyqf2bkoetxtt8j9dx7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `invoices` */

insert  into `invoices`(`id`,`created_at`,`description`,`observation`,`client_id`,`user_id`) values (3,'2022-04-18','xa','cac',5,2),(4,'2022-04-23','Laminas','laminas',5,2),(5,'2022-04-23','Laminas','laminas',1,2),(6,'2022-04-23','Varillas','varilas',5,2),(7,'2022-04-23','Varillas','dss',1,7),(8,'2022-04-25','bloque','bloque',1,2),(9,'2022-04-30','Varillas','<sca',1,2),(10,'2022-04-30','va alu','axa',2,2),(11,'2022-04-30','la hi','dadca',7,2),(12,'2022-05-13','aasda','daaxa',1,2);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `products` */

insert  into `products`(`id`,`created_at`,`enable`,`iva`,`name`,`price`,`stock`) values (1,NULL,'',0,'Varilla Hierro 3x3',25999,5),(2,NULL,'',0,'Varilla Aluminio 3x3',22555,4),(3,NULL,'',0,'Lamina Aluminio 2x2',14999,5),(4,'2022-04-17','',2,'Sony Notebook Z110',37990,0),(5,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(6,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(7,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,1),(8,'2022-04-17','',4,'Panasonic Pantalla LCD',259990,2),(9,'2022-04-17','',5,'Sony Camara digital DSC-W320B',123490,2),(10,'2022-04-17','',3,'Apple iPod shuffle',1499990,2),(11,'2022-04-17','',2,'Sony Notebook Z110',37990,0),(12,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(13,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(14,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,2),(15,'2022-04-17','',4,'Panasonic Pantalla LCD',259990,2),(16,'2022-04-17','',5,'Sony Camara digital DSC-W320B',123490,2),(17,'2022-04-17','',3,'Apple iPod shuffle',1499990,2),(18,'2022-04-17','',2,'Sony Notebook Z110',37990,2),(19,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(20,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(21,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,2),(22,'2022-04-23','',0,'lamina lata 2ss2',12123,5),(23,NULL,'\0',0,'Bloque 2x2',1233,4);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enable` bit(1) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`enable`,`password`,`username`) values (1,'\0',NULL,'andres'),(2,'','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','admin'),(3,'','$2a$10$DmuCBnGToz4wYmaJ5Czi3OPDKtTC1W.SMzmnLyJj0pOTHvqdxHPzG','sain'),(6,'','$2a$10$yrY4bPVtj2YboQAhsb4n4uOyIxFlIelwLNZKWYFh9rqSITfSE/Tiq','pedro'),(7,'\0',NULL,'luis'),(8,'','$2a$10$I5TR14a.dnzLATvRiB023OoWpHEvOp.vyO.V1jqp.ZFm9S3U0iyi.','pablo');

/* Procedure structure for procedure `mas_vendidos` */





/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
