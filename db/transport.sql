/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.7.17-0ubuntu0.16.04.1 : Database - transport
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`transport` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `transport`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(100) COLLATE utf8_bin NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `country_id` (`country_id`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `city` */

insert  into `city`(`id`,`city`,`country_id`) values (1,'Минск',1),(2,'Москва',2),(3,'Киев',3),(4,'Бангкок',4),(5,'Патайа',4),(6,'Пхукет',4),(7,'София',5),(8,'Банско',5);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `country` */

insert  into `country`(`id`,`country`) values (1,'Беларусь'),(2,'Россия'),(3,'Украина'),(4,'Тайланд'),(5,'Болгария');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `role` */

insert  into `role`(`id`,`role`) values (1,'admin'),(2,'manager'),(3,'user');

/*Table structure for table `route` */

DROP TABLE IF EXISTS `route`;

CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transport_id` int(11) NOT NULL,
  `from` int(11) NOT NULL,
  `to` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `time_departure` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transport_id` (`transport_id`),
  KEY `from` (`from`),
  KEY `to` (`to`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`transport_id`) REFERENCES `transport` (`id`),
  CONSTRAINT `route_ibfk_2` FOREIGN KEY (`from`) REFERENCES `city` (`id`),
  CONSTRAINT `route_ibfk_3` FOREIGN KEY (`to`) REFERENCES `city` (`id`),
  CONSTRAINT `route_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `route` */

insert  into `route`(`id`,`transport_id`,`from`,`to`,`status_id`,`time_departure`,`arrival_time`) values (1,1,2,4,1,'2017-03-30 22:54:53','2017-03-31 07:32:00'),(2,2,1,7,1,'2017-04-27 05:38:00','2017-04-27 09:32:00'),(3,3,3,8,1,'2017-05-05 00:00:00','2017-05-06 01:00:00'),(4,4,1,2,1,'2017-06-24 23:00:00','2017-06-25 09:12:00');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `status` */

insert  into `status`(`id`,`status`) values (1,'Свободен'),(2,'Готовится'),(3,'В пути'),(4,'Прибыл');

/*Table structure for table `ticket` */

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `route_id` int(11) NOT NULL,
  `reservation_status` tinyint(4) NOT NULL,
  `date_reservation` datetime NOT NULL,
  `pay_status` tinyint(4) NOT NULL,
  `date_pay` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `ticket_ibfk_2` (`route_id`),
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ticket` */

insert  into `ticket`(`id`,`user_id`,`route_id`,`reservation_status`,`date_reservation`,`pay_status`,`date_pay`) values (1,3,1,1,'2017-03-18 15:32:00',0,NULL),(2,4,4,1,'2017-03-29 12:30:30',0,NULL);

/*Table structure for table `transport` */

DROP TABLE IF EXISTS `transport`;

CREATE TABLE `transport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transport_type_id` int(11) NOT NULL,
  `model` varchar(100) COLLATE utf8_bin NOT NULL,
  `capacity` int(11) NOT NULL,
  `speed` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transport_type_id` (`transport_type_id`),
  CONSTRAINT `transport_ibfk_1` FOREIGN KEY (`transport_type_id`) REFERENCES `transport_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `transport` */

insert  into `transport`(`id`,`transport_type_id`,`model`,`capacity`,`speed`) values (1,1,'Боинг',258,'900'),(2,1,'Ил-96-30',262,'900'),(3,1,'Airbus A310',183,'858'),(4,2,'Электропоезд',350,'80'),(5,2,'Дизель',480,'90'),(6,2,'Паровоз',300,'70'),(7,3,'MERCEDES-BENZ 0305',35,'90'),(8,3,'MAN LION S COACH L R08\r\n',32,'90'),(9,4,'Грузопассажирское судно',380,'40');

/*Table structure for table `transport_type` */

DROP TABLE IF EXISTS `transport_type`;

CREATE TABLE `transport_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `transport_type` */

insert  into `transport_type`(`id`,`type`) values (1,'Самолет'),(2,'Поезд'),(3,'Автобус'),(4,'Корабль');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `login` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`login`,`email`,`password`) values (1,'Михаил','admin','admin@gmail.com','21232f297a57a5a743894a0e4a801fc3'),(2,'Алена','manager','manager@gmail.com','1d0258c2440a8d19e716292b231e3190'),(3,'Василий','vasia','vasia@gmail.com','e10adc3949ba59abbe56e057f20f883e'),(4,'Тимофей','timoha','timoga@yandex.ru','e10adc3949ba59abbe56e057f20f883e');

/*Table structure for table `user_to_role` */

DROP TABLE IF EXISTS `user_to_role`;

CREATE TABLE `user_to_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `user_to_role_ibfk_2` (`id_role`),
  CONSTRAINT `user_to_role_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_to_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_to_role` */

insert  into `user_to_role`(`id_user`,`id_role`) values (1,1),(1,2),(2,2),(1,3),(2,3),(3,3),(4,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
