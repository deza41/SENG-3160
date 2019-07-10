DROP schema IF EXISTS prototype;
create schema prototype;
USE  prototype;

DROP TABLES IF EXISTS  eventsTimes, eventsImage, tagReferences, tag,  likedBusinesses, recommendationContent, recommendations,  relationBusinessANDReview, review, relationUserNotification, relationBusinessUserNotification, notifications, businessImageContent,eventBooking, dealBooking, events, package, deals, businessUser, categories, user;

CREATE TABLE `user` (
  `userName` VARCHAR(255) NOT NULL UNIQUE,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(255)  NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `postCode` VARCHAR(100),
  `passWord` VARCHAR(255) NOT NULL,
  `profileImageURL` VARCHAR(255) NOT NULL,
  `admin` boolean DEFAULT FALSE,
  `userID` int(10) UNIQUE NOT NULL AUTO_INCREMENT,
  `dateOfBirth` date,
  CONSTRAINT `User_pk` PRIMARY KEY (`userID`)
);

CREATE TABLE `categories`(
  `categoriesID` int(10) NOT NULL UNIQUE AUTO_INCREMENT,
  `category` VARCHAR(255) NOT NULL,
  CONSTRAINT `Categories_Pk` PRIMARY KEY (`categoriesID`)
);

CREATE TABLE `businessUser` (
  `userName` VARCHAR(255) NOT NULL UNIQUE,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(255)  NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `postCode` VARCHAR(100),
  `password` VARCHAR(255) NOT NULL,
  `profileImageURL` VARCHAR(255) NOT NULL,
  `businessID` int(10) UNIQUE NOT NULL AUTO_INCREMENT,
  `businessName` VARCHAR(255) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `number` int(4) NOT NULL,
  `unit` VARCHAR(255),
  `suburb` VARCHAR(255) NOT NULL,
  `categoryID` int(10) NOT NULL,
  `businessDescription` VARCHAR(255) NOT NULL,
  `businessOpen` TIME NOT NULL,
  `businessClose` TIME NOT NULL,
  `rating` int(3) default 0,
  `businessViews` int default 0,
  `latitude` VARCHAR(255),
  `longitude` VARCHAR(255),
  CONSTRAINT `Business_pk` PRIMARY KEY (`businessID`)
);

CREATE TABLE `deals` (
  `dealID` int(10) NOT NULL AUTO_INCREMENT,
  `businessID`  int(10) NOT NULL,
  `dealTitle` VARCHAR(255) NOT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `validDuration` int(3) NOT NULL,
  `dealDescription` VARCHAR(255) NOT NULL,
  `dealImageURL` VARCHAR(255) NOT NULL,
  `activeDeal` boolean DEFAULT TRUE,
  `price` double NOT NULL,
  `oldPrice` double NOT NULL,
  `totalPurchased` int DEFAULT 0,
  `totalViews` int DEFAULT 0,
  CONSTRAINT `Deal_pk` PRIMARY KEY (`dealID`),
  CONSTRAINT `Business_fk`  FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `package`(
  `packageID` int(10) NOT NULL AUTO_INCREMENT,
  `userID` int(10) NOT NULL,
  CONSTRAINT `Package_pk` PRIMARY KEY (`packageID`),
  CONSTRAINT `User_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `events`(
  `eventID` int(10) NOT NULL AUTO_INCREMENT,
  `businessID` int(10) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `categoriesID` int(10) NOT NULL,
  `eventDescription` VARCHAR(255) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `price` double DEFAULT 0,
  `oldPrice` double DEFAULT 0,
  `activeEvent` boolean DEFAULT TRUE,
  `totalViews` int DEFAULT 0,
  CONSTRAINT `Event_Pk` PRIMARY KEY (`eventID`),
  CONSTRAINT `categories_fk` FOREIGN KEY (`categoriesID`) REFERENCES `categories` (`categoriesID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `business_fk5` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `eventsTimes`(
  `eventID` int(10)  NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME NOT NULL,
  `daysDate` DATE,
  CONSTRAINT `eventTimes_pk` PRIMARY KEY (`eventID`, `daysDate`),
  CONSTRAINT `events_fk` FOREIGN KEY (`eventID`) REFERENCES `events` (`eventID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `eventsImage` (
  `eventID` int(10)  NOT NULL,
  `eventImageURL` VARCHAR(255),
  `profilePicture` boolean DEFAULT FALSE,
  CONSTRAINT `eventImage_pk` PRIMARY KEY (`eventID`),
  CONSTRAINT `events_fk2` FOREIGN KEY (`eventID`) REFERENCES `events` (`eventID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `dealBooking` (
  `packageID` int(10) NOT NULL,
  `bookingID` int(10) NOT NULL AUTO_INCREMENT,
  `businessID` int(10) NOT NULL,
  `dealID` int(10) NOT NULL,
  `startTime` TIME,
  `endTime` TIME,
  `date` DATE ,
  `numberOfAdults` int,
  `numberOfChildren` int,
  `bookingStatus` int(1) DEFAULT 0,
  `valid` boolean DEFAULT TRUE,
  `qrCodeURL` VARCHAR(255),
  CONSTRAINT `DealBooking_pk` PRIMARY KEY (`bookingID`),
  CONSTRAINT `Deal_fk` FOREIGN KEY (`dealID`) REFERENCES `deals` (`dealID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Package_fk` FOREIGN KEY (`packageID`) REFERENCES `package` (`packageID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Business_fk8` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `eventBooking` (
  `bookingID` int(10) NOT NULL AUTO_INCREMENT,
  `packageID` int(10) NOT NULL,
  `businessID` int(10) NOT NULL,
  `eventID` int(10) NOT NULL,
  `startTime` TIME,
  `endTime` TIME,
  `date` DATE ,
  `bookingValid` boolean DEFAULT FALSE,
  `valid` boolean DEFAULT TRUE,
  `qrCodeURL` VARCHAR(255) ,
  CONSTRAINT `EventsBooking_pk` PRIMARY KEY (`bookingID`),
  CONSTRAINT `Event_fk` FOREIGN KEY (`eventID`) REFERENCES `events` (`eventID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Package_fk2` FOREIGN KEY (`packageID`) REFERENCES `package` (`packageID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Business_fk10` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `businessImageContent` (
  `businessID` int(10)  NOT NULL,
  `businessImageURL` VARCHAR(255),
  CONSTRAINT `BuinessImage_pk` PRIMARY KEY (`businessID`),
  CONSTRAINT `Business_fk9` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `notifications`(
  `notificationID` int(10) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `link` VARCHAR(255),
  CONSTRAINT `Notification_Pk` PRIMARY KEY (`notificationID`)
);

CREATE TABLE `relationUserNotification`(
  `notificationID` int(10) NOT NULL,
  `userID` int(10)  NOT NULL,
  `viewed` boolean DEFAULT FALSE,
  `type` int NOT NULL,
  CONSTRAINT `Notification_fk` FOREIGN KEY (`notificationID`) REFERENCES `notifications` (`notificationID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `User_fk1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `relationBusinessUserNotification`(
  `notificationID` int(10) NOT NULL,
  `businessID` int(10)  NOT NULL,
  `type` int NOT NULL,
  CONSTRAINT `Notification_fk3` FOREIGN KEY (`notificationID`) REFERENCES `notifications` (`notificationID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Business_fk11` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `review`(
  `reviewID` int(10) NOT NULL AUTO_INCREMENT,
  `userID` int(10) NOT NULL,
  `businessID` int(10) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `rating` int(10) NOT NULL,
  `details` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  CONSTRAINT `Review_Pk` PRIMARY KEY (`reviewID`),
  CONSTRAINT `User_fk2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Business_fk6` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `recommendations`(
  `recommendationID` int(10) NOT NULL AUTO_INCREMENT,
  `userID` int(10) NOT NULL,
  CONSTRAINT `Recommendation_Pk` PRIMARY KEY (`recommendationID`),
  CONSTRAINT `User_fk3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `recommendationContent`(
  `recommendationID` int(10) NOT NULL,
  `dealID` int(10) NOT NULL,
  CONSTRAINT `Recommendation_fk` FOREIGN KEY (`recommendationID`) REFERENCES `recommendations` (`recommendationID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Deal_fk2` FOREIGN KEY (`dealID`) REFERENCES `deals` (`dealID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `likedBusinesses` (
  `businessID` int(10) NOT NULL,
  `userID` int(10) NOT NULL,
  `liked` boolean NOT NULL,
  CONSTRAINT `businessID_fk7` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `User_fk4` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE `tag`(
  `tagID` int(10) NOT NULL UNIQUE,
  `tag` VARCHAR(255) NOT NULL,
  CONSTRAINT `Tag_Pk` PRIMARY KEY (`tagID`)
);

CREATE TABLE `tagReferences`(
  `tagID` int(10) NOT NULL UNIQUE,
  `businessID` int(10) NOT NULL,
  CONSTRAINT `businessTags_Pk` PRIMARY KEY (`tagID`),
  CONSTRAINT `Business_fk7` FOREIGN KEY (`businessID`) REFERENCES `businessUser` (`businessID`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

--------------- Dummy data ------------------

INSERT INTO User (userName, firstName, lastName, phoneNumber, email, postCode, passWord, profileImageURL, admin) VALUES
('admin', 'newcastle', 'connect', '0405656544', 'newcastleConnect@gmail.com', '2245', 'password', 'http://www.newcastleConnect.com/img/src/23', TRUE),
('swagmasta', 'alex', 'perry', '0496858467', 'swagGanga@hotmail.com', '2234', 'thicc', 'http://www.google.com/eggs', FALSE),
('rich1122', 'richard', 'belling', '49567674', 'r1chardiscool@hotmail.com', '2456','richisdabest', 'none', FALSE);

INSERT INTO categories(category) VALUES
  ('food'),
  ('experience'),
  ('accommodation'),
  ('transport');

INSERT INTO businessUser (userName, firstName, lastName, phoneNumber, email, postCode, password, profileImageURL, businessName, street, number, unit, suburb, categoryID, businessDescription, businessOpen, businessClose,latitude, longitude, rating)  VALUES
  ('johnstan', 'john', 'stan', '49565643', 'whiteblueresturant@hotmail.com', '2332', 'white4345', 'http://whiteblueresturant.com/img', 'White Blue Lagoon', 'stanford', '76', 'none', 'newcastle', '1' , 'very yummy food!', '09:00:00', '21:00:00', '-32.434', '45.43', 70);

INSERT INTO businessUser (userName, firstName, lastName, phoneNumber, email, postCode, password, profileImageURL, businessName, street, number, unit, suburb, categoryID, businessDescription, businessOpen, businessClose,latitude, longitude)  VALUES
  ('rafiel', 'rafiel', 'eldamos', '49323235', 'bronosresturant@hotmail.com', '2354', 'eldamos12', 'http://bronos.com/img/src/banner', 'Bronos', 'hunter', '234', 'none', 'newcastle', '1', 'Authentic food for the family! Specials every monday!', '10:00:00', '23:30:00', '32.33', '43.3443'),
  ('alex', 'alex', 'meron', '493223', 'skyfallINQ@gmail.com', '2376', 'falldown11', 'http:/experiencesky.com/re3r33ff3/e2ddf2f2/e2we2e', 'businessName', 'meron', '5', 'none', 'newcastle', '2', 'Want to do sky diving? Want to experince a thrill?', '07:00:00', '18:00:00', '13.54', '76.43');

INSERT INTO businessImageContent (businessID, businessImageURL) VALUES
  (2, 'http://bronos.com/img/se3df'),
  (3,'http:/experiencesky.com/re3r33ff3/f32ff/cache'),
  (1,'http://whiteblueresturant.com/img/d23d2');

INSERT INTO Deals (businessID, dealTitle, startDate, endDate, validDuration, dealDescription, dealImageURL, activeDeal, price, oldPrice) VALUES
  ('1', 'Sunday Special', '2017-06-25 06:00:00', '2017-06-25 20:00:00', 10,'$10 fish and chips deal!', 'http://www.whiteblueresturant.com/dealsImg', TRUE,10, 15),
  ('1', 'Cheap Tuesdays', '2017-06-23 12:00:00', '2017-06-23 18:00:00', 999,'Tuesday lunch specials', 'http://bronos.com/deals/src/', TRUE,16,20),
  ('3', 'Weekend Deals!', '2017-06-20 00:00:00', '2017-06-23 00:00:00', 50,'Weekend thrills are now cheaper for a limited time!', 'http:/experiencesky.com/32rf33f2/e2ddf2f2/', TRUE,23, 30);

INSERT INTO Package (userID) VALUES
  ('1'),
  ('2'),
  ('3');

INSERT INTO Notifications (description, title, link) VALUES
  ('Welcome To Newcastle Connections, We are happy that you have deceided to join in the masses and become a member. experience newcastle greatest deals', 'Welcome New User', " "),
  ('Your package has been fully Confirmed', 'Confirmation', ' ');

INSERT INTO RelationUserNotification (notificationID, userID, type) VALUES
  (1, 1, 0),
  (2, 1, 1),
  (1, 2, 0),
  (1, 3, 0);

INSERT INTO relationBusinessUserNotification(notificationID, businessID, type) VALUES
  ( 1, 1, 0);

INSERT INTO review (businessID, userID, title, rating, details, date, time) VALUES
  ('1','1', 'The best!', '8', 'One of the best places I have ever eaten!', '2017-06-20', '11:31:00');

INSERT INTO Recommendations(UserID) VALUES
  ('1');

INSERT INTO RecommendationContent(recommendationID, dealID) VALUES
  ('1', '1');

INSERT INTO events(businessID, title, categoriesID, eventDescription, startDate, endDate, price, oldPrice) VALUES
  ('1', 'The Best event of all days', '1', 'This is a tribute to the best event in the world', '2017-06-10 ', '2017-06-13 ', 150.00, 100);

INSERT INTO eventstimes(eventID, startTime, endTime, daysDate) VALUES
  ('1', '08:00:00', '22:30:00', '2017-06-10'),
  ('1', '08:00:00', '22:30:00', '2017-06-11'),
  ('1', '08:00:00', '22:30:00', '2017-06-12'),
  ('1', '08:00:00', '22:30:00', '2017-06-13');

INSERT INTO eventsImage(eventID,eventImageURL,profilePicture) VALUES
  ('1', 'testing url', true);


