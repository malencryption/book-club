# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.17)
# Database: bookClub
# Generation Time: 2014-07-14 18:44:06 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`accountId`),
  UNIQUE KEY `email` (`email`),
  KEY `userId` (`userId`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`accountId`, `email`, `password`, `userId`)
VALUES
	(1,'test@test.com','testuser',1);

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table author
# ------------------------------------------------------------

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `authorId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;

INSERT INTO `author` (`authorId`, `firstName`, `lastName`)
VALUES
	(1,'J.K.','Rowling'),
	(2,'Dan','Brown'),
	(3,'Dinesh','D\'Souza'),
	(4,'John','Green'),
	(5,'John','Brooks'),
	(7,'Veronica','Roth'),
	(8,'Edward','Klein'),
	(9,'Laura','Hillenbrand');

/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table authorlookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `authorlookup`;

CREATE TABLE `authorlookup` (
  `lookupId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bookId` int(10) unsigned NOT NULL,
  `authorId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`lookupId`),
  KEY `bookId` (`bookId`,`authorId`),
  KEY `authorId` (`authorId`),
  CONSTRAINT `authorlookup_ibfk_2` FOREIGN KEY (`authorId`) REFERENCES `author` (`authorId`),
  CONSTRAINT `authorlookup_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `authorlookup` WRITE;
/*!40000 ALTER TABLE `authorlookup` DISABLE KEYS */;

INSERT INTO `authorlookup` (`lookupId`, `bookId`, `authorId`)
VALUES
	(1,1,1),
	(2,2,2),
	(3,3,1),
	(4,4,1),
	(5,5,1),
	(6,6,1),
	(7,7,1),
	(8,9,1),
	(9,10,3),
	(10,11,4),
	(11,13,9),
	(12,14,5),
	(13,15,7);

/*!40000 ALTER TABLE `authorlookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`bookId`, `title`)
VALUES
	(1,'Harry Potter and the Sorcerer\'s Stone'),
	(2,'The Da Vinci Code'),
	(3,'Harry Potter and the Chamber of Secrets'),
	(4,'Harry Potter and the Prisoner of Azkaban'),
	(5,'Harry Potter and the Goblet of Fire'),
	(6,'Harry Potter and the Order of the Pheonix'),
	(7,'Harry Potter and the Half-Blood Prince'),
	(9,'Harry Potter and the Deathly Hollows'),
	(10,'America: Imagine a World without Her'),
	(11,'The Fault in Our Stars'),
	(13,'Unbroken: A World War II Story of Survival, Resilience, and Redemption'),
	(14,'Business Adventures: Twelve Classic Tales from the World of Wall Street'),
	(15,'Four: A Divergent Collection');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table club
# ------------------------------------------------------------

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `clubId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateCreated` date NOT NULL,
  `bookId` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`clubId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;

INSERT INTO `club` (`clubId`, `dateCreated`, `bookId`, `name`)
VALUES
	(1,'2014-07-07',1,'Harry Potter 1');

/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table clubmember
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clubmember`;

CREATE TABLE `clubmember` (
  `memberId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clubId` int(10) unsigned NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`memberId`),
  KEY `memberId` (`memberId`,`clubId`,`accountId`),
  KEY `clubId` (`clubId`),
  KEY `accountId` (`accountId`),
  CONSTRAINT `clubmember_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`),
  CONSTRAINT `clubmember_ibfk_2` FOREIGN KEY (`clubId`) REFERENCES `club` (`clubId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `clubmember` WRITE;
/*!40000 ALTER TABLE `clubmember` DISABLE KEYS */;

INSERT INTO `clubmember` (`memberId`, `clubId`, `accountId`)
VALUES
	(1,1,1);

/*!40000 ALTER TABLE `clubmember` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `commentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(300) NOT NULL,
  `date` date NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  `postId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `memberId` (`accountId`),
  KEY `postId` (`postId`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;

INSERT INTO `comment` (`commentId`, `content`, `date`, `accountId`, `postId`)
VALUES
	(1,'your post is a test','2014-07-07',1,1);

/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table genre
# ------------------------------------------------------------

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
  `genreId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`genreId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;

INSERT INTO `genre` (`genreId`, `name`)
VALUES
	(1,'fantasy'),
	(2,'mystery'),
	(3,'Classic'),
	(4,'Comic'),
	(5,'Detective'),
	(6,'Fable'),
	(7,'Fairy Tale'),
	(8,'Folklore'),
	(9,'Historical Fiction'),
	(10,'Horror'),
	(11,'Humor'),
	(12,'MetaFiction'),
	(13,'Poetry'),
	(14,'Realistic Fiction'),
	(15,'Science Fiction'),
	(16,'Thriller'),
	(17,'Biography'),
	(18,'Textbook'),
	(19,'Politics'),
	(20,'Romance'),
	(21,'Business');

/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table genrelookup
# ------------------------------------------------------------

DROP TABLE IF EXISTS `genrelookup`;

CREATE TABLE `genrelookup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bookid` int(11) unsigned NOT NULL,
  `genreid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookidc` (`bookid`),
  KEY `genreidc` (`genreid`),
  CONSTRAINT `genreidc` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genreId`),
  CONSTRAINT `bookidc` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `genrelookup` WRITE;
/*!40000 ALTER TABLE `genrelookup` DISABLE KEYS */;

INSERT INTO `genrelookup` (`id`, `bookid`, `genreid`)
VALUES
	(0,7,1),
	(1,1,1),
	(2,1,2),
	(3,1,16),
	(4,3,1),
	(5,3,2),
	(6,3,16),
	(7,4,1),
	(8,4,2),
	(9,4,16),
	(10,5,1),
	(11,5,2),
	(12,5,16),
	(13,10,19),
	(14,14,21),
	(15,6,1),
	(16,6,2),
	(17,6,16),
	(18,7,2),
	(19,7,16),
	(20,7,1),
	(21,9,1),
	(22,9,2),
	(23,9,16),
	(24,11,20),
	(25,13,17),
	(26,15,15),
	(27,15,20);

/*!40000 ALTER TABLE `genrelookup` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `postId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`postId`),
  KEY `memberId` (`accountId`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;

INSERT INTO `post` (`postId`, `title`, `content`, `date`, `accountId`)
VALUES
	(1,'test post','this is a post ','2014-07-07',1);

/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`userId`, `firstName`, `lastName`)
VALUES
	(1,'test','user');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
