
CREATE DATABASE bookdb CHAR SET utf8;
USE bookdb ;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookImg` varchar(200) NOT NULL,
  `bookName` varchar(50) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `saleCount` int(11) DEFAULT NULL,
  `bookCount` int(11) DEFAULT NULL,
  `bookStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */
/*Status 1 means the book is on sale*/
insert  into `t_book`(`id`,`bookImg`,`bookName`,`price`,`author`,`saleCount`,`bookCount`,`bookStatus`) values 
(1,'java.jpg','Java: The Complete Reference',65.00,'Herbert Schildt',10,100,0),(2,'theThreeBodyProblem.jpg','The Three Body',48.95,'Cixin Liu',18,89,0),
(3,'theLordOfRings.jpg','The Lord of Rings',50.00,'J.R.R.Tolkien',12,143,0),(4,'mysql.jpg','Murach''s MySQL',70.00,'Joel Murach',3,98,0),
(5,'management.jpg','High Output Managemet',30.00,'Andrew S. Grove',2,99,0),(6,'historyOfTheWorldMap.jpg','History of the World',47.00,'DK and Smithsonian',5,100,0),
(7,'historyofCanada.jpg','A History of Canada',45.00,'Adam Shoalts',3,100,0),(8,'theArts.jpg','The Arts: A Visual Encyclopedia',19.00,'DK',50,200,0),
(9,'classicalLiterature.jpg','Classical Literature',9.00,'William Allan',5,30,0),(10,'shakespeare.jpg','The Complete Works of William Shakespeare',35.00,'William Shakespeare',10,89,0),
(11,'businessMadeSimple.jpg','Business Made Simple',23.00,'Donald Miller',5,99,0);

insert  into `t_book`(`id`,`bookImg`,`bookName`,`price`,`author`,`saleCount`,`bookCount`,`bookStatus`) values 
(12,'lesMisérables.jpg','Les Misérables',55.00,'Victor Hugo',12,120,1),(13,'dataStructuresandAlgorithms.jpg','Data Structures and Algorithms Made Easy',45.65,'Narasimha Karumanchi',18,70,1),
(14,'theLittlePrince.jpg','The Little Prince',40.00,' Antoine de Saint-Exupery',20,140,1),
(15,'japaneseGrammar.jpg','Dict of Basic Japanese Grammar',65.00,'Seiichi Makino',2,50,1),
(16,'SPQR.jpg','S.P.Q.R.: A History of Ancient Rome',60.00,'Mary Beard',6,80,1),
(17,'stockMarket.jpg','A Beginner''s Guide to the Stock Market',25.00,'Matthew R. Kratter',7,90,1),
(18,'programmingPHP.jpg','Dict of Basic Japanese Grammar',39.00,'Kevin Tatroe',1,40,1),
(19,'blockchainForDummies.jpg','Blockchain For Dummies',25.00,'Tiana Laurence',4,72,1);


CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;


/*Data for the table `t_user` */
/*role 0 means normal users, role 1 means admins*/
insert  into `t_user`(`id`,`uname`,`pwd`,`email`,`role`) values (1,'Alice','alice321','Alice321@gmail.com',0),(2,'David','david123','david123@gmail.com',1);


/*Table structure for table `t_cart_item` */

CREATE TABLE `t_cart_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `userBean` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_book` (`book`),
  KEY `FK_cart_user` (`userBean`),
  CONSTRAINT `FK_cart_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  CONSTRAINT `FK_cart_user` FOREIGN KEY (`userBean`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order` */

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(128) NOT NULL,
  `orderDate` datetime DEFAULT NULL,
  `orderUser` int(11) DEFAULT NULL,
  `orderMoney` double(10,2) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`orderNo`),
  KEY `FK_order_user` (`orderUser`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`orderUser`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`orderNo`,`orderDate`,`orderUser`,`orderMoney`,`orderStatus`) values (4,'5eaab6146dc54e0482fdb8b6120c229b_20211025112519','2021-10-25 11:25:20',1,506.90,0),(5,'f5a22aac925d42eabc6b49c45a3eb74f_20211025113004','2021-10-25 11:30:04',1,48.95,0),(6,'8a245df4359e4224b531cf121c4acab3_20211025113019','2021-10-25 11:30:20',1,0.00,0),(7,'b521cd49ab2943f0bbc0630c95978f1c_20211025113039','2021-10-25 11:30:40',1,48.95,0),(8,'d4f366a82cd4491c9899b181753804b4_20211025113151','2021-10-25 11:31:52',1,48.95,0),(9,'8f5869a839f4483e947bd2c3163f3c23_20211025113159','2021-10-25 11:31:59',1,48.95,0),(10,'c5fcd95dbe7f49669f96b4ad6444ae6b_20211025120531','2021-10-25 12:05:32',1,147.95,0),(11,'6240ec3e5ac04e3583e1beb75a9e94ec_20211025120542','2021-10-25 12:05:42',1,147.95,0);

/*Table structure for table `t_order_item` */

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `orderBean` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_detail_book` (`book`),
  KEY `FK_detail_order` (`orderBean`),
  CONSTRAINT `FK_detail_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  CONSTRAINT `FK_detail_order` FOREIGN KEY (`orderBean`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`id`,`book`,`buyCount`,`orderBean`) values (6,1,1,4),(7,2,2,4),(8,10,1,4),(9,3,5,4),(10,4,1,4),(11,2,1,5),(12,2,1,7),(13,2,1,8),(14,2,1,9),(15,1,1,10),(16,2,1,10),(17,1,1,11),(18,2,1,11);

