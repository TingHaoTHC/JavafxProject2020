CREATE DATABASE  IF NOT EXISTS `project`;
USE `project`;

DROP TABLE IF EXISTS `Staff`;

CREATE TABLE `Staff` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(30) NOT NULL UNIQUE,
  `UserPass` varchar(30) NOT NULL,
  `Pin` int(4) NOT NULL default 1234,
  `Role` varchar(10) NOT NULL,
  `Locked` varchar(10) default 'UNLOCKED',
  `LockAttempt` int(10) default 10,
  `Email` varchar(30) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `Staff` VALUES 
	(1,'admin','password',1234,'admin','UNLOCKED', 10,'admin@gmail.com'),
    (2,'user','password',1111,'user','LOCKED', 10,'user@gmail.com'),
    (3,'super','password',2222,'super','UNLOCKED', 10,'admin@gmail.com');

select * from Staff;
select Role from Staff where UserName='admin' and UserPass='password'; 