[
"CREATE DATABASE IF NOT EXISTS `volleyballteammanagement`"

"USE `volleyballteammanagement`;"

"DROP TABLE IF EXISTS `league`;"

"CREATE TABLE `league` (
  `leagueId` int NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`leagueId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;"

"LOCK TABLES `league` WRITE;"

"INSERT INTO `league` VALUES (1,'First'),(2,'Second'),(3,'FirstA'),(4,'SecondA');"

"UNLOCK TABLES;"

"DROP TABLE IF EXISTS `position`;"

"CREATE TABLE `position` (
  `positionId` int NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`positionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;"

"LOCK TABLES `position` WRITE;"

"INSERT INTO `position` VALUES (1,'Setter'),(2,'Outside Hitter'),(3,'Opposite Hitter'),(4,'Middle Blocker'),(5,'Libero'),(6,'Deffensive Specialist');"

"UNLOCK TABLES;"

"DROP TABLE IF EXISTS `teams`;"

"CREATE TABLE `teams` (
  `teamId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `leagueId` int NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `foundationDate` datetime NOT NULL,
  PRIMARY KEY (`teamId`),
  KEY `league` (`leagueId`),
  CONSTRAINT `league` FOREIGN KEY (`leagueId`) REFERENCES `league` (`leagueId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;"

"LOCK TABLES `teams` WRITE;"

"INSERT INTO `teams` VALUES (1,'Karadjordje','Topola',1,'Serbia','1964-06-25 00:00:00'),(2,'Dinamo MOSCOW','Topola',2,'Russia','1914-02-15 00:00:00'),(3,'Panathinaikos AC ATHENS','Athens',3,'Greece','1970-04-04 00:00:00');"

"UNLOCK TABLES;"

"DROP TABLE IF EXISTS `players`;"

"CREATE TABLE `players` (
  `playerId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateOfBirth` datetime NOT NULL,
  `height` int NOT NULL,
  `teamId` int NOT NULL,
  `positionId` int NOT NULL,
  PRIMARY KEY (`playerId`),
  KEY `position` (`positionId`),
  KEY `teams` (`teamId`),
  CONSTRAINT `position` FOREIGN KEY (`positionId`) REFERENCES `position` (`positionId`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `teams` FOREIGN KEY (`teamId`) REFERENCES `teams` (`teamId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;"

"LOCK TABLES `players` WRITE;"

"INSERT INTO `players` VALUES 
(1,'Wilfredo Leon','1993-07-31 00:00:00',202,1,2),
(2,'Tijana Boskovic','1994-05-07 00:00:00',193,2,3),
(3,'Ivan Zaytsev','1981-06-07 00:00:00',201,3,4),
(4,'Ervin Ngapeth','1980-07-21 00:00:00',197,2,5),
(5,'Aleksa Markovic','1999-01-16 00:00:00',189,1,1);"

"UNLOCK TABLES;"
]
