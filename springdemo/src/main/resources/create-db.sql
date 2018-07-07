DROP SCHEMA IF EXISTS `hbstudent`;

CREATE SCHEMA `hbstudent`;

use hb_student_tracker;

DROP TABLE IF EXISTS `WeatherLog`;

CREATE TABLE `WeatherLog` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `response_id` varchar(45) UNIQUE NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `actual_weather` varchar(45) DEFAULT NULL,
  `temperature` varchar(45) DEFAULT NULL,
  `d_time_inserted` timestamp DEFAULT current_timestamp,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;