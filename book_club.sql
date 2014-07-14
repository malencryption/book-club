-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2014 at 02:40 PM
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
  `password` varchar(50) NOT NULL,
  `userId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`accountId`),
  UNIQUE KEY `email` (`email`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountId`, `email`, `password`, `userId`) VALUES
(1, 'test@test.com', 'testuser', 1);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `authorId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`authorId`, `firstName`, `lastName`) VALUES
(1, 'J.K.', 'Rowling'),
(2, 'Dan', 'Brown');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `authorlookup`
--

INSERT INTO `authorlookup` (`lookupId`, `bookId`, `authorId`) VALUES
(1, 1, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `bookId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `genreId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`bookId`),
  KEY `genreId` (`genreId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bookId`, `title`, `genreId`) VALUES
(1, 'Harry Potter and the Sorcerer''s Stone', 1),
(2, 'The Da Vinci Code', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`clubId`, `dateCreated`, `bookId`, `name`) VALUES
(1, '2014-07-07', 1, 'Harry Potter 1'),
(2, '2014-07-11', NULL, 'home');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`commentId`, `content`, `date`, `accountId`, `postId`) VALUES
(1, 'your post is a test', '2014-07-07', 1, 1),
(2, 'yes', '2014-07-14', 1, 1),
(3, 'thank you', '2014-07-14', 1, 1),
(4, 'hello', '2014-07-14', 1, 2),
(5, 'you are welcome', '2014-07-14', 1, 1),
(6, 'I agree', '2014-07-14', 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `genreId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`genreId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreId`, `name`) VALUES
(1, 'fantasy'),
(2, 'mystery');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `postId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` varchar(200) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accountId` int(10) unsigned NOT NULL,
  `clubId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`postId`),
  KEY `memberId` (`accountId`),
  KEY `clubId` (`clubId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`postId`, `title`, `content`, `date`, `accountId`, `clubId`) VALUES
(1, 'test post', 'this is a post ', '2014-07-07 00:00:00', 1, 1),
(2, 'Home Post', 'test', '2014-07-11 00:00:00', 1, 2),
(3, 'gummy', 'worms', '2014-07-14 05:49:47', 1, 1),
(4, 'peep', 'yumm', '2014-07-14 05:54:12', 1, 1),
(5, 'workin', 'it out', '2014-07-14 05:55:45', 1, 1),
(6, 'rocky road', 'go go', '2014-07-14 05:57:30', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `firstName`, `lastName`) VALUES
(1, 'test', 'user');

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
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`genreId`) REFERENCES `genre` (`genreId`);

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
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`clubId`) REFERENCES `club` (`clubId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
