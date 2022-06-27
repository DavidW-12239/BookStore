-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2022 at 02:24 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_book`
--

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL,
  `bookImg` varchar(200) DEFAULT NULL,
  `bookName` varchar(50) DEFAULT NULL,
  `curr_price` double(10,2) DEFAULT NULL,
  `orig_price` double(10,2) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `saleCount` int(11) DEFAULT NULL,
  `bookCount` int(11) DEFAULT NULL,
  `bookStatus` int(11) NOT NULL,
  `category` varchar(200) DEFAULT NULL,
  `review` double DEFAULT NULL,
  `review_num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_book`
--

INSERT INTO `t_book` (`id`, `bookImg`, `bookName`, `curr_price`, `orig_price`, `author`, `saleCount`, `bookCount`, `bookStatus`, `category`, `review`, `review_num`) VALUES
(1, 'java.jpg', 'Java: The Complete Reference', 65.00, 65.00, 'Herbert Schildt', 11, 9, 0, 'Technology', 4.666666666666667, 3),
(2, 'theThreeBodyProblem.jpg', 'The Three Body', 48.95, 48.95, 'Cixin Liu', 19, 88, 0, 'Fiction', 3.8, 5),
(3, 'theLordOfRings.jpg', 'The Lord of Rings', 50.00, 50.00, 'J.R.R.Tolkien', 26, 129, 0, 'Fiction, Literature', 4.333333333333333, 6),
(4, 'mysql.jpg', 'Murach\'s MySQL', 70.00, 70.00, 'Joel Murach', 17, 84, 0, 'Technology', 5, 5),
(5, 'management.jpg', 'High Output Managemet', 30.00, 30.00, 'Andrew S. Grove', 4, 97, 0, 'Management', 3.3333333333333335, 3),
(6, 'historyOfTheWorldMap.jpg', 'History of the World', 47.00, 47.00, 'DK and Smithsonian', 5, 100, 0, 'History', 1.5, 2),
(7, 'historyofCanada.jpg', 'A History of Canada ', 45.00, 45.00, 'Adam Shoalts', 4, 99, 0, 'History', 2.5, 2),
(8, 'theArts.jpg', 'The Arts: A Visual Encyclopedia', 19.00, 19.00, 'DK', 51, 139, 0, 'Art', 5, 2),
(9, 'classicalLiterature.jpg', 'Classical Literature', 9.00, 9.00, 'William Allan', 5, 30, 0, 'Literature', 3, 1),
(10, 'shakespeare.jpg', 'The Complete Works of William Shakespeare', 45.00, 55.00, 'William Shakespeare', 10, 88, 1, 'Literature', 0, 0),
(11, 'businessMadeSimple.jpg', 'Business Made Simple', 23.00, 23.00, 'Donald Miller', 5, 80, 0, 'Business', 2, 1),
(12, 'lesMisérables.jpg', '', 55.00, 62.00, 'Victor Hugo', 12, 120, 1, 'Fiction, Literature', 0, 0),
(13, 'dataStructuresandAlgorithms.jpg', 'Data Structures and Algorithms', 45.00, 60.00, 'Narasimha Karumanchi', 18, 70, 1, 'Technology', 0, 0),
(14, 'theLittlePrince.jpg', 'The Little Prince', 40.00, 50.00, 'Antoine de Saint-Exupery', 20, 140, 1, 'Fiction, Literature', 0, 0),
(15, 'japaneseGrammar.jpg', 'Dict of Basic Japanese Grammar', 42.00, 65.00, 'Seiichi Makino ', 2, 50, 1, 'Education', 0, 0),
(16, 'SPQR.jpg', 'S.P.Q.R.: A History of Ancient Rome', 30.00, 40.00, 'Mary Beard', 6, 80, 1, 'History', 0, 0),
(17, 'stockMarket.jpg', 'A Beginner\'s Guide to the Stock Market', 25.00, 40.00, 'Matthew R. Kratter', 7, 90, 1, 'Finance', 0, 0),
(18, 'programmingPHP.jpg', 'Dict of Basic Japanese Grammar', 40.00, 50.00, 'Kevin Tatroe', 1, 40, 1, 'Technology', 0, 0),
(19, 'blockchainForDummies.jpg', 'Blockchain For Dummies', 25.00, 32.00, 'Tiana Laurence', 4, 72, 1, 'Finance', 0, 0),
(21, NULL, 'aaa', 11.00, 15.00, 'Wang', 9, 0, 1, 'cba, sss', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `t_cart_item`
--

CREATE TABLE `t_cart_item` (
  `id` int(11) NOT NULL,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `userBean` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_cart_item`
--

INSERT INTO `t_cart_item` (`id`, `book`, `buyCount`, `userBean`) VALUES
(38, 5, 1, 1),
(40, 6, 2, 1),
(42, 3, 1, 1),
(43, 4, 2, 1),
(44, 10, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_order`
--

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL,
  `orderNo` varchar(128) NOT NULL,
  `orderDate` datetime DEFAULT NULL,
  `orderUser` int(11) DEFAULT NULL,
  `orderMoney` double(10,2) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_order`
--

INSERT INTO `t_order` (`id`, `orderNo`, `orderDate`, `orderUser`, `orderMoney`, `orderStatus`) VALUES
(4, '5eaab6146dc54e0482fdb8b6120c229b_20211025112519', '2021-10-25 11:25:20', 1, 506.90, 0),
(5, 'f5a22aac925d42eabc6b49c45a3eb74f_20211025113004', '2021-10-25 11:30:04', 1, 48.95, 1),
(7, 'b521cd49ab2943f0bbc0630c95978f1c_20211025113039', '2021-10-25 11:30:40', 1, 48.95, 1),
(8, 'd4f366a82cd4491c9899b181753804b4_20211025113151', '2021-10-25 11:31:52', 1, 48.95, 1),
(9, '8f5869a839f4483e947bd2c3163f3c23_20211025113159', '2021-10-25 11:31:59', 1, 48.95, 0),
(10, 'c5fcd95dbe7f49669f96b4ad6444ae6b_20211025120531', '2021-10-25 12:05:32', 1, 147.95, 0),
(11, '6240ec3e5ac04e3583e1beb75a9e94ec_20211025120542', '2021-10-25 12:05:42', 1, 147.95, 0),
(68, '573ebc0a-d181-44c9-b3af-45e0a51b8ed7_Sun Jun 26 13:01:29 PDT 2022', '2022-06-26 13:01:29', 2, 60.00, 5),
(69, '495abbb9-d469-4c85-9d6e-6060f4f6b94f_Sun Jun 26 13:17:01 PDT 2022', '2022-06-26 13:17:01', 2, 163.95, 5),
(72, 'b3238e17-fb2f-47fd-a56b-e19bb4a58d9c_Sun Jun 26 17:23:34 PDT 2022', '2022-06-26 17:23:34', 2, 64.00, 5);

-- --------------------------------------------------------

--
-- Table structure for table `t_order_item`
--

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `orderBean` int(11) DEFAULT NULL,
  `isReviewed` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_order_item`
--

INSERT INTO `t_order_item` (`id`, `book`, `buyCount`, `orderBean`, `isReviewed`) VALUES
(6, 1, 1, 4, 1),
(7, 2, 2, 4, 0),
(8, 10, 1, 4, 0),
(9, 3, 5, 4, 0),
(10, 4, 1, 4, 0),
(11, 2, 1, 5, 0),
(12, 2, 1, 7, 0),
(13, 2, 1, 8, 0),
(14, 2, 1, 9, 0),
(15, 1, 1, 10, 0),
(16, 2, 1, 10, 0),
(17, 1, 1, 11, 0),
(18, 2, 1, 11, 0),
(90, 5, 2, 68, 1),
(91, 2, 1, 69, 1),
(92, 3, 1, 69, 1),
(93, 1, 1, 69, 1),
(94, 8, 1, 72, 1),
(95, 7, 1, 72, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id`, `uname`, `pwd`, `email`, `role`) VALUES
(1, 'Alice', 'alice321', 'Alice321@gmail.com', 0),
(2, 'David', 'david123', 'david123@gmail.com', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_book`
--
ALTER TABLE `t_book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_cart_item`
--
ALTER TABLE `t_cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cart_book` (`book`),
  ADD KEY `FK_cart_user` (`userBean`);

--
-- Indexes for table `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `orderNo` (`orderNo`),
  ADD KEY `FK_order_user` (`orderUser`);

--
-- Indexes for table `t_order_item`
--
ALTER TABLE `t_order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_detail_book` (`book`),
  ADD KEY `FK_detail_order` (`orderBean`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uname` (`uname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_book`
--
ALTER TABLE `t_book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `t_cart_item`
--
ALTER TABLE `t_cart_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT for table `t_order`
--
ALTER TABLE `t_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `t_order_item`
--
ALTER TABLE `t_order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=96;

--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_cart_item`
--
ALTER TABLE `t_cart_item`
  ADD CONSTRAINT `FK_cart_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  ADD CONSTRAINT `FK_cart_user` FOREIGN KEY (`userBean`) REFERENCES `t_user` (`id`);

--
-- Constraints for table `t_order`
--
ALTER TABLE `t_order`
  ADD CONSTRAINT `FK_order_user` FOREIGN KEY (`orderUser`) REFERENCES `t_user` (`id`);

--
-- Constraints for table `t_order_item`
--
ALTER TABLE `t_order_item`
  ADD CONSTRAINT `FK_detail_book` FOREIGN KEY (`book`) REFERENCES `t_book` (`id`),
  ADD CONSTRAINT `FK_detail_order` FOREIGN KEY (`orderBean`) REFERENCES `t_order` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
