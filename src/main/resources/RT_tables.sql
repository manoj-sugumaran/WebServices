-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for ringtone
CREATE DATABASE IF NOT EXISTS `ringtone` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ringtone`;


-- Dumping structure for table ringtone.appconfig
CREATE TABLE IF NOT EXISTS `appconfig` (
  `MODULE` varchar(100) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  `OS_TYPE` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`MODULE`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.app_user
CREATE TABLE IF NOT EXISTS `app_user` (
  `FIREBASE_UID` varchar(100) NOT NULL,
  `USERNAME` varchar(75) DEFAULT NULL,
  `MOBILENO` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(75) DEFAULT NULL,
  `UID` varchar(100) NOT NULL,
  `AUTHENTICATION_TYPE` varchar(500) DEFAULT NULL,
  `FACEBOOK_ID` varchar(75) DEFAULT NULL,
  `TWITTER_HANDLE` varchar(75) DEFAULT NULL,
  `PROFILE_URL` varchar(250) DEFAULT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED` timestamp NULL DEFAULT NULL,
  `OS_VERSION` varchar(200) DEFAULT NULL,
  `APP_VERSION` text NOT NULL,
  `LOCATION` varchar(200) DEFAULT NULL,
  `PHOTO_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PASSWORD_MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PLAYER_ID` varchar(500) DEFAULT NULL,
  `IS_ACTIVE` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`FIREBASE_UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.channelmap
CREATE TABLE IF NOT EXISTS `channelmap` (
  `CHANNELID` varchar(100) NOT NULL,
  `CONTENTID` varchar(100) NOT NULL,
  `CONTENTORDER` bigint(10) NOT NULL,
  `CONTEXT_START_DATE` datetime DEFAULT NULL,
  `CONTEXT_END_DATE` datetime DEFAULT NULL,
  `CONTEXT_TRIVIA` text,
  `CONTEXT_TEASER` text,
  `CONTEXT_IMAGE_URL` varchar(250) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CHANNELID`,`CONTENTID`),
  KEY `channelmap_ibfk_2` (`CONTENTID`),
  CONSTRAINT `channelmap_ibfk_1` FOREIGN KEY (`CHANNELID`) REFERENCES `channels` (`CHANNELID`),
  CONSTRAINT `channelmap_ibfk_2` FOREIGN KEY (`CONTENTID`) REFERENCES `content` (`CONTENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.channels
CREATE TABLE IF NOT EXISTS `channels` (
  `CHANNELID` varchar(100) NOT NULL,
  `NAME` varchar(500) DEFAULT NULL,
  `ARTWORKURL` varchar(500) DEFAULT NULL,
  `STARTDATE` datetime NOT NULL,
  `ENDDATE` datetime NOT NULL,
  `CHANNELORDER` int(6) DEFAULT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  `module` varchar(255) NOT NULL,
  `os_type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CHANNELID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.content
CREATE TABLE IF NOT EXISTS `content` (
  `CONTENTID` varchar(100) NOT NULL,
  `SONGNAME` varchar(500) NOT NULL,
  `LANGUAGE` varchar(500) DEFAULT NULL,
  `ALBUMNAME` varchar(500) DEFAULT NULL,
  `SINGER` varchar(500) DEFAULT NULL,
  `CONTENT_URL_RT` varchar(500) DEFAULT NULL,
  `CONTENT_URL_FT` varchar(500) DEFAULT NULL,
  `ARTWORKURL` varchar(500) DEFAULT NULL,
  `ISRC` varchar(500) DEFAULT NULL,
  `GENRE` varchar(500) DEFAULT NULL,
  `PROMOCODE` varchar(500) DEFAULT NULL,
  `CONTENTPROVIDER` varchar(500) DEFAULT NULL,
  `RIGHTSBODY` varchar(500) DEFAULT NULL,
  `LABELNAME` varchar(500) DEFAULT NULL,
  `SONGRELEASEDATE` datetime DEFAULT NULL,
  `STARTDATE` datetime DEFAULT NULL,
  `ENDDATE` datetime DEFAULT NULL,
  `SUBTYPE` varchar(45) NOT NULL,
  `SONG_TEASER` text,
  `SONG_TRIVIA` text,
  `SONG_TAGS` varchar(500) DEFAULT NULL,
  `MARKETING_LABEL` varchar(45) DEFAULT NULL,
  `PARENTAL_ADVISORY` varchar(45) DEFAULT NULL,
  `CHARGE_CLASS` varchar(45) DEFAULT NULL,
  `CUT_ALLOWED` int(11) DEFAULT NULL,
  `CONTENT_IDENTIFIER` varchar(45) DEFAULT NULL,
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIEDTIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CONTENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.downloaded_tracks
CREATE TABLE IF NOT EXISTS `downloaded_tracks` (
  `ID` varchar(36) NOT NULL,
  `FIREBASE_UID` varchar(50) NOT NULL,
  `SUBSCRIPTION_ID` varchar(50) NOT NULL,
  `CONTENTID` varchar(50) NOT NULL,
  `CUT_START_TIME` int(11) NOT NULL,
  `CUT_END_TIME` int(11) NOT NULL,
  `DOWNLOADEDTIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_downloaded_tracks_discover_user` (`FIREBASE_UID`),
  KEY `FK_downloaded_tracks_content` (`CONTENTID`),
  KEY `FK_downloaded_tracks_subscriptions` (`SUBSCRIPTION_ID`),
  CONSTRAINT `FK_downloaded_tracks_content` FOREIGN KEY (`CONTENTID`) REFERENCES `content` (`CONTENTID`),
  CONSTRAINT `FK_downloaded_tracks_discover_user` FOREIGN KEY (`FIREBASE_UID`) REFERENCES `app_user` (`FIREBASE_UID`),
  CONSTRAINT `FK_downloaded_tracks_subscriptions` FOREIGN KEY (`SUBSCRIPTION_ID`) REFERENCES `subscriptions` (`SUBSCRIPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.subscriptions
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `SUBSCRIPTION_ID` varchar(50) NOT NULL,
  `NAME` text NOT NULL,
  `PRICE` text NOT NULL,
  `BILLING_PERIOD_DAYS` int(11) NOT NULL,
  `FREE_TRIAL_DAYS` int(11) DEFAULT '0',
  `DOWNLOAD_LIMIT` int(11) NOT NULL,
  PRIMARY KEY (`SUBSCRIPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.


-- Dumping structure for table ringtone.user_subscription
CREATE TABLE IF NOT EXISTS `user_subscription` (
  `ID` varchar(36) NOT NULL,
  `FIREBASE_UID` varchar(100) NOT NULL,
  `SUBSCRIPTION_ID` varchar(50) NOT NULL,
  `SUBSCRIPTION_START_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SUBSCRIPTION_END_DATE` datetime NOT NULL,
  `NUMBER_OF_DOWNLOADS` int(11) NOT NULL DEFAULT '0',
  `SUBSCRIPTION_TYPE` text NOT NULL,
  `STATUS` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `FK_user_subscription_discover_user` (`FIREBASE_UID`),
  KEY `FK_user_subscription_subscriptions` (`SUBSCRIPTION_ID`),
  CONSTRAINT `FK_user_subscription_discover_user` FOREIGN KEY (`FIREBASE_UID`) REFERENCES `app_user` (`FIREBASE_UID`),
  CONSTRAINT `FK_user_subscription_subscriptions` FOREIGN KEY (`SUBSCRIPTION_ID`) REFERENCES `subscriptions` (`SUBSCRIPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
