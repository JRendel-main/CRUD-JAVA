CREATE DATABASE IF NOT EXISTS `library_db`;
USE `library_db`;

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
`id` int NOT NULL ,
`title` varchar(45) NOT NULL unique,
`author` varchar(64) NOT NULL,
`year`  int,
`pages` int,

primary key (`id`)
) engine=InnoDB DEFAULT CHARSET=latin1;

USE `library_db`;

select * from `book`;