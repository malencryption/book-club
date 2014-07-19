-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2014 at 03:04 AM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `book_club`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `accountId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `userId` int(10) unsigned NOT NULL,
  `fbAccessToken` varchar(500) DEFAULT NULL,
  `fbId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`accountId`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `fbId` (`fbId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountId`, `email`, `password`, `userId`, `fbAccessToken`, `fbId`) VALUES
(1, 'test@test.com', 'testuser', 1, NULL, NULL),
(2, 'runn3rgirl36@gmail.com', NULL, 5, NULL, '10152126187021576');

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `authorId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`authorId`, `firstName`, `lastName`) VALUES
(1, 'J.K.', 'Rowling'),
(2, 'Dan', 'Brown'),
(3, 'Dinesh', 'D''Souza'),
(4, 'John', 'Green'),
(5, 'John', 'Brooks'),
(7, 'Veronica', 'Roth'),
(8, 'Edward', 'Klein'),
(9, 'Laura', 'Hillenbrand');

-- --------------------------------------------------------

--
-- Table structure for table `authorlookup`
--

CREATE TABLE IF NOT EXISTS `authorlookup` (
  `lookupId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bookId` int(10) unsigned NOT NULL,
  `authorId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`lookupId`),
  KEY `bookId` (`bookId`,`authorId`),
  KEY `authorId` (`authorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `authorlookup`
--

INSERT INTO `authorlookup` (`lookupId`, `bookId`, `authorId`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 9, 1),
(9, 10, 3),
(10, 11, 4),
(11, 13, 9),
(12, 14, 5),
(13, 15, 7);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `bookId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookId`, `title`) VALUES
(1, 'Harry Potter and the Sorcerer''s Stone'),
(2, 'The Da Vinci Code'),
(3, 'Harry Potter and the Chamber of Secrets'),
(4, 'Harry Potter and the Prisoner of Azkaban'),
(5, 'Harry Potter and the Goblet of Fire'),
(6, 'Harry Potter and the Order of the Pheonix'),
(7, 'Harry Potter and the Half-Blood Prince'),
(9, 'Harry Potter and the Deathly Hollows'),
(10, 'America: Imagine a World without Her'),
(11, 'The Fault in Our Stars'),
(13, 'Unbroken: A World War II Story of Survival, Resilience, and Redemption'),
(14, 'Business Adventures: Twelve Classic Tales from the World of Wall Street'),
(15, 'Four: A Divergent Collection');

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE IF NOT EXISTS `club` (
  `clubId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateCreated` date NOT NULL,
  `bookId` int(10) unsigned DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`clubId`),
  KEY `bookId` (`bookId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`clubId`, `dateCreated`, `bookId`, `name`) VALUES
(1, '2014-07-07', 1, 'Harry Potter 1'),
(3, '2014-07-18', NULL, 'home');

-- --------------------------------------------------------

--
-- Table structure for table `clubmember`
--

CREATE TABLE IF NOT EXISTS `clubmember` (
  `memberId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clubId` int(10) unsigned NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`memberId`),
  KEY `memberId` (`memberId`,`clubId`,`accountId`),
  KEY `clubId` (`clubId`),
  KEY `accountId` (`accountId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `clubmember`
--

INSERT INTO `clubmember` (`memberId`, `clubId`, `accountId`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(300) NOT NULL,
  `date` date NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  `postId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`commentId`),
  KEY `memberId` (`accountId`),
  KEY `postId` (`postId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `content`, `date`, `accountId`, `postId`) VALUES
(1, 'your post is a test', '2014-07-07', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `genreId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`genreId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreId`, `name`) VALUES
(1, 'fantasy'),
(2, 'mystery'),
(3, 'Classic'),
(4, 'Comic'),
(5, 'Detective'),
(6, 'Fable'),
(7, 'Fairy Tale'),
(8, 'Folklore'),
(9, 'Historical Fiction'),
(10, 'Horror'),
(11, 'Humor'),
(12, 'MetaFiction'),
(13, 'Poetry'),
(14, 'Realistic Fiction'),
(15, 'Science Fiction'),
(16, 'Thriller'),
(17, 'Biography'),
(18, 'Textbook'),
(19, 'Politics'),
(20, 'Romance'),
(21, 'Business');

-- --------------------------------------------------------

--
-- Table structure for table `genrelookup`
--

CREATE TABLE IF NOT EXISTS `genrelookup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bookid` int(11) unsigned NOT NULL,
  `genreid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookidc` (`bookid`),
  KEY `genreidc` (`genreid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `genrelookup`
--

INSERT INTO `genrelookup` (`id`, `bookid`, `genreid`) VALUES
(0, 7, 1),
(1, 1, 1),
(2, 1, 2),
(3, 1, 16),
(4, 3, 1),
(5, 3, 2),
(6, 3, 16),
(7, 4, 1),
(8, 4, 2),
(9, 4, 16),
(10, 5, 1),
(11, 5, 2),
(12, 5, 16),
(13, 10, 19),
(14, 14, 21),
(15, 6, 1),
(16, 6, 2),
(17, 6, 16),
(18, 7, 2),
(19, 7, 16),
(20, 7, 1),
(21, 9, 1),
(22, 9, 2),
(23, 9, 16),
(24, 11, 20),
(25, 13, 17),
(26, 15, 15),
(27, 15, 20);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `postId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  `clubId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`postId`),
  KEY `memberId` (`accountId`),
  KEY `clubId` (`clubId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postId`, `title`, `content`, `date`, `accountId`, `clubId`) VALUES
(1, 'test post', 'this is a post ', '2014-07-07', 1, 1),
(2, 'Home Post', 'test', '2014-07-18', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `firstName`, `lastName`) VALUES
(1, 'test', 'user'),
(5, 'Leesa', 'Ruzicka');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `authorlookup`
--
ALTER TABLE `authorlookup`
  ADD CONSTRAINT `authorlookup_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`),
  ADD CONSTRAINT `authorlookup_ibfk_2` FOREIGN KEY (`authorId`) REFERENCES `author` (`authorId`);

--
-- Constraints for table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `club_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`);

--
-- Constraints for table `clubmember`
--
ALTER TABLE `clubmember`
  ADD CONSTRAINT `clubmember_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`),
  ADD CONSTRAINT `clubmember_ibfk_2` FOREIGN KEY (`clubId`) REFERENCES `club` (`clubId`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`);

--
-- Constraints for table `genrelookup`
--
ALTER TABLE `genrelookup`
  ADD CONSTRAINT `bookidc` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookId`),
  ADD CONSTRAINT `genreidc` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genreId`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`clubId`) REFERENCES `club` (`clubId`),
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
