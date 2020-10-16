CREATE TABLE `KTS_ACTION` (
  `actionGuid` varchar(50) NOT NULL,
  `testcaseGuid` varchar(50) DEFAULT NULL,
  `isStart` enum('Y','N') DEFAULT 'N',
  `nextActionGuid` varchar(50) DEFAULT NULL,
  `actionId` varchar(50) NOT NULL,
  `x` float DEFAULT NULL,
  `y` float DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `deleted` enum('Y','N') DEFAULT 'N',
  PRIMARY KEY (`actionGuid`),
  KEY `ACTION_ID` (`actionId`),
  CONSTRAINT `KTS_ACTION_ibfk_1` FOREIGN KEY (`actionId`) REFERENCES `KTS_ACTION_TEMPLATE` (`actionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `KTS_ACTION_PROPERTY` (
  `propertySeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `propertyName` varchar(30) NOT NULL,
  `propertyValue` varchar(30) NOT NULL,
  `actionGuid` varchar(50) NOT NULL,
  `deleted` enum('Y','N') DEFAULT 'N',
  PRIMARY KEY (`propertySeq`),
  KEY `ACTION_GUID` (`actionGuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `KTS_ACTION_PROPERTY_TEMPLATE` (
  `actionId` varchar(50) NOT NULL,
  `propertyName` varchar(30) NOT NULL,
  `propertyValue` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`actionId`,`propertyName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `KTS_ACTION_TEMPLATE` (
  `actionId` varchar(50) NOT NULL,
  `type` enum('FATCH','DECODE','EXECUTION','MEMORY','RESERVED') DEFAULT NULL,
  `templateDescription` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`actionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `KTS_TEST` (
  `testSeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `deleted` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`testSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `KTS_TESTCASE` (
  `testcaseGuid` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `deleted` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`testcaseGuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TEST_REL_TESTCASE` (
  `relationSeq` bigint(20) NOT NULL AUTO_INCREMENT,
  `testSeq` bigint(20) NOT NULL,
  `listIndex` bigint(20) NOT NULL,
  `testcaseGuid` varchar(50) NOT NULL,
  `deleted` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`relationSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;