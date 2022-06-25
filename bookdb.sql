-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2022 at 08:08 PM
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
(1, 'java.jpg', 'Java: The Complete Reference', 65.00, 65.00, 'Herbert Schildt', 10, 10, 0, 'Technology', NULL, 0),
(2, 'theThreeBodyProblem.jpg', 'The Three Body', 48.95, 48.95, 'Cixin Liu', 18, 89, 0, 'Fiction', NULL, 0),
(3, 'theLordOfRings.jpg', 'The Lord of Rings', 50.00, 50.00, 'J.R.R.Tolkien', 24, 131, 0, 'Fiction, Literature', NULL, 0),
(4, 'mysql.jpg', 'Murach\'s MySQL', 70.00, 70.00, 'Joel Murach', 15, 86, 0, 'Technology', NULL, 0),
(5, 'management.jpg', 'High Output Managemet', 30.00, 30.00, 'Andrew S. Grove', 2, 99, 0, 'Management', NULL, 0),
(6, 'historyOfTheWorldMap.jpg', 'History of the World', 47.00, 47.00, 'DK and Smithsonian', 5, 100, 0, 'History', NULL, 0),
(7, 'historyofCanada.jpg', 'A History of Canada ', 45.00, 45.00, 'Adam Shoalts', 3, 100, 0, 'History', NULL, 0),
(8, 'theArts.jpg', 'The Arts: A Visual Encyclopedia', 19.00, 19.00, 'DK', 50, 140, 0, 'Art', NULL, 0),
(9, 'classicalLiterature.jpg', 'Classical Literature', 9.00, 9.00, 'William Allan', 5, 30, 0, 'Literature', NULL, 0),
(10, 'shakespeare.jpg', 'The Complete Works of William Shakespeare', 45.00, 55.00, 'William Shakespeare', 10, 88, 1, 'Literature', NULL, 0),
(11, 'businessMadeSimple.jpg', 'Business Made Simple', 23.00, 23.00, 'Donald Miller', 5, 80, 0, 'Business', NULL, 0),
(12, 'lesMisérables.jpg', 'Les Misérables', 55.00, 62.00, 'Victor Hugo', 12, 120, 1, 'Fiction, Literature', NULL, 0),
(13, 'dataStructuresandAlgorithms.jpg', 'Data Structures and Algorithms', 45.00, 60.00, 'Narasimha Karumanchi', 18, 70, 1, 'Technology', NULL, 0),
(14, 'theLittlePrince.jpg', 'The Little Prince', 40.00, 50.00, 'Antoine de Saint-Exupery', 20, 140, 1, 'Fiction, Literature', NULL, 0),
(15, 'japaneseGrammar.jpg', 'Dict of Basic Japanese Grammar', 42.00, 65.00, 'Seiichi Makino ', 2, 50, 1, 'Education', NULL, 0),
(16, 'SPQR.jpg', 'S.P.Q.R.: A History of Ancient Rome', 30.00, 40.00, 'Mary Beard', 6, 80, 1, 'History', NULL, 0),
(17, 'stockMarket.jpg', 'A Beginner\'s Guide to the Stock Market', 25.00, 40.00, 'Matthew R. Kratter', 7, 90, 1, 'Finance', NULL, 0),
(18, 'programmingPHP.jpg', 'Dict of Basic Japanese Grammar', 40.00, 50.00, 'Kevin Tatroe', 1, 40, 1, 'Technology', NULL, 0),
(19, 'blockchainForDummies.jpg', 'Blockchain For Dummies', 25.00, 32.00, 'Tiana Laurence', 4, 72, 1, 'Finance', NULL, 0),
(21, NULL, 'aaa', 11.00, 15.00, 'Wang', 9, 0, 1, 'cba, sss', NULL, 0);

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
(44, 10, 1, 1),
(103, 4, 1, 2);

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
(59, '50268ddd-238f-4585-bdb0-44543fa22880_Fri Jun 24 18:03:43 PDT 2022', '2022-06-24 18:03:43', 2, 120.00, 0),
(64, '3be6e026-363e-4691-badd-64c9c82947c3_Fri Jun 24 18:25:09 PDT 2022', '2022-06-24 18:25:09', 2, 70.00, 0),
(66, '23eff8d0-47a1-4c14-88f2-ec00909de199_Fri Jun 24 18:31:50 PDT 2022', '2022-06-24 18:31:50', 2, 70.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table `t_order_item`
--

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL,
  `book` int(11) DEFAULT NULL,
  `buyCount` int(11) DEFAULT NULL,
  `orderBean` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_order_item`
--

INSERT INTO `t_order_item` (`id`, `book`, `buyCount`, `orderBean`) VALUES
(6, 1, 1, 4),
(7, 2, 2, 4),
(8, 10, 1, 4),
(9, 3, 5, 4),
(10, 4, 1, 4),
(11, 2, 1, 5),
(12, 2, 1, 7),
(13, 2, 1, 8),
(14, 2, 1, 9),
(15, 1, 1, 10),
(16, 2, 1, 10),
(17, 1, 1, 11),
(18, 2, 1, 11),
(83, 4, 1, 59),
(84, 3, 1, 59),
(86, 4, 1, 64),
(87, 4, 1, 66);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT for table `t_order`
--
ALTER TABLE `t_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `t_order_item`
--
ALTER TABLE `t_order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

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
