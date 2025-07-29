-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2025 at 02:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `room_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `booking_date` date NOT NULL,
  `time_slot` enum('Tiết 1-2','Tiết 3-4','Tiết 5-6','Tiết 7-8','Tiết 9-10') NOT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `purpose` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`id`, `user_id`, `room_id`, `booking_date`, `time_slot`, `subject`, `class_name`, `purpose`, `created_at`) VALUES
(1, 2, 1, '2025-07-30', 'Tiết 1-2', 'Toán ứng dụng CNTT', '23Nh16', 'Thực hành ', '2025-07-28 23:17:56'),
(2, 5, 1, '2025-07-30', 'Tiết 3-4', 'Cơ sở dữ liệu', '23Nh99', 'Thực hành SQL cơ bản', '2025-07-28 23:17:56'),
(3, 3, 1, '2025-07-30', 'Tiết 5-6', 'Mạng máy tính', 'CNTT03', 'Lab Cisco Packet Tracer', '2025-07-28 23:17:56');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `computer_count` int(11) NOT NULL DEFAULT 0,
  `description` text DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `room_name`, `computer_count`, `description`, `status`, `created_at`) VALUES
(1, 'Phòng C204', 30, 'Phòng máy tính C204 tầng 2', 'ACTIVE', '2025-07-28 23:04:34'),
(2, 'Phòng D102', 25, 'Phòng thí nghiệm vật lí D102 tầng 1', 'ACTIVE', '2025-07-28 23:04:34');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `role` enum('ADMIN','TEACHER') NOT NULL DEFAULT 'TEACHER',
  `status` enum('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `role`, `status`, `created_at`) VALUES
(1, 'admin', 'admin123', 'Quản trị viên hệ thống', 'ADMIN', 'ACTIVE', '2025-07-28 22:53:50'),
(2, 'gv001', 'pass123', 'Phạm Công Thắng', 'TEACHER', 'ACTIVE', '2025-07-28 22:53:50'),
(3, 'gv002', 'pass123', 'Nguyễn Thế Xuân Ly', 'TEACHER', 'ACTIVE', '2025-07-28 22:53:50'),
(4, 'gv003', 'pass123', 'Đỗ Thị Tuyết Hoa', 'TEACHER', 'ACTIVE', '2025-07-28 22:53:50'),
(5, 'gv004', 'pass123', 'Võ Đức Hoàng', 'TEACHER', 'ACTIVE', '2025-07-28 22:53:50'),
(6, 'gv005', 'pass123', 'Nguyễn Văn A', 'TEACHER', 'INACTIVE', '2025-07-28 22:53:50');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_booking` (`room_id`,`booking_date`,`time_slot`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
