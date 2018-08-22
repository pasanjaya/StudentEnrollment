-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2018 at 05:45 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentenrollment`
--

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `int_id` int(11) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`int_id`, `fname`, `lname`, `gender`, `dob`, `phone`) VALUES
(1, 'Thilak', 'Silva', 'M', '1991-04-07', '0710235438'),
(2, 'Nayomi', 'Abyewardane', 'F', '1991-08-01', '0713467453'),
(3, 'Sadaruwan', 'Darshana', 'M', '1990-02-19', '0714546352'),
(4, 'Thiluka', 'Marapana', 'F', '1992-07-24', '0718749325');

-- --------------------------------------------------------

--
-- Table structure for table `instructorlab`
--

CREATE TABLE `instructorlab` (
  `int_id` int(11) NOT NULL,
  `lab_id` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructorlab`
--

INSERT INTO `instructorlab` (`int_id`, `lab_id`) VALUES
(1, 'A'),
(3, 'A'),
(4, 'B');

-- --------------------------------------------------------

--
-- Table structure for table `instructorsubject`
--

CREATE TABLE `instructorsubject` (
  `sub_id` varchar(12) NOT NULL,
  `int_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructorsubject`
--

INSERT INTO `instructorsubject` (`sub_id`, `int_id`) VALUES
('CS/1101', 2),
('CS/1102', 4),
('CS/1103', 4),
('CS/1201', 3),
('CS/1204', 1);

-- --------------------------------------------------------

--
-- Table structure for table `lab`
--

CREATE TABLE `lab` (
  `lab_id` varchar(1) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lab`
--

INSERT INTO `lab` (`lab_id`, `capacity`) VALUES
('A', 40),
('B', 40),
('C', 30),
('D', 20),
('E', 10);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `lec_id` int(11) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`lec_id`, `fname`, `lname`, `gender`, `dob`, `phone`) VALUES
(1, 'Kamal', 'Dias', 'M', '1956-05-25', '0719456325'),
(2, 'Surendran', 'Siverajan', 'M', '1963-07-18', '0715464372'),
(3, 'Anurada', 'Jayawaradana', 'M', '1989-03-17', '0775463453'),
(4, 'Sapumali', 'Silva', 'F', '1990-04-19', '0776757445');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_id` varchar(4) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `capacity`) VALUES
('N401', 260),
('S202', 200),
('W001', 150),
('W002', 200);

-- --------------------------------------------------------

--
-- Table structure for table `ugcourse`
--

CREATE TABLE `ugcourse` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(100) NOT NULL,
  `faculty_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ugcourse`
--

INSERT INTO `ugcourse` (`course_id`, `course_name`, `faculty_name`) VALUES
(1, 'BSc (Honours) in Computer Science', 'SCHOOL OF COMPUTING'),
(2, 'BSc (Honours) in Software Engineering', 'SCHOOL OF COMPUTING'),
(3, 'BSc (Honours) Engineering in Computer Systems Engineering', 'SCHOOL OF ENGINEERING'),
(4, 'BSc in Business Management (Human Resource Management) (Special)', 'SCHOOL OF BUSINESS'),
(5, 'BSc (Honours) in Computer Networks', 'SCHOOL OF COMPUTING'),
(6, 'Bachelor of Interior Design', 'SCHOOL OF ENGINEERING'),
(7, 'BSc in Business Management (Logistics Management) (Special)', 'SCHOOL OF BUSINESS');

-- --------------------------------------------------------

--
-- Table structure for table `uglogin`
--

CREATE TABLE `uglogin` (
  `username` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `stu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uglogin`
--

INSERT INTO `uglogin` (`username`, `email`, `pass`, `stu_id`) VALUES
('2018/ug/12', 'pasan@gmail.com', '1234', 12),
('2018/ug/13', 'bandaranayake11@gmail.com', 'Isuru1995#', 13),
('2018/ug/14', 'udani@gmail.com', '1234', 14);

-- --------------------------------------------------------

--
-- Table structure for table `ugstudent`
--

CREATE TABLE `ugstudent` (
  `stu_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `address` varchar(150) NOT NULL,
  `year` varchar(4) NOT NULL,
  `syllabus` varchar(6) NOT NULL,
  `indexno` varchar(8) NOT NULL,
  `zscore` varchar(6) NOT NULL,
  `rank` varchar(11) NOT NULL,
  `subject1` varchar(100) NOT NULL,
  `grade1` varchar(1) NOT NULL,
  `subject2` varchar(100) NOT NULL,
  `grade2` varchar(1) NOT NULL,
  `subject3` varchar(100) NOT NULL,
  `grade3` varchar(1) NOT NULL,
  `course_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ugstudent`
--

INSERT INTO `ugstudent` (`stu_id`, `name`, `fullname`, `dob`, `gender`, `nic`, `address`, `year`, `syllabus`, `indexno`, `zscore`, `rank`, `subject1`, `grade1`, `subject2`, `grade2`, `subject3`, `grade3`, `course_id`) VALUES
(12, 'GBP Jayawickrama', 'Pasan Jayawickrama', '1994-04-03', 'Male', '940940230V', 'Narammala', '2015', 'Local', '5742226', '2.2116', '5', 'Combined Mathematics', 'B', 'Physics', 'B', 'ICT', 'A', 1),
(13, 'K.W.M.R.I.A.Bandaranayake', 'Kohona Weerakoon Mudiyanse Ralahamillage Isuru Akalanka Bandaranayake', '1995-05-27', 'Male', '951482269V', '\"Thisara\",Nekeththa,Gokarella', '2015', 'Local', '5525543', '1.5887', '2142', 'Combined Mathematics', 'B', 'Physics', 'B', 'Chemistry', 'C', 1),
(14, 'G.A.U Warnakulasooriya', 'Gonawala Arachchige Udani Warnakulasooriya', '1995-06-06', 'Female', '950232443v', 'Kubukgete', '2015', 'Local', '1509094', '1.6789', '234', 'Commerce', 'B', 'Logic', 'B', 'Business', 'A', 4);

-- --------------------------------------------------------

--
-- Table structure for table `ugstudentsubject`
--

CREATE TABLE `ugstudentsubject` (
  `stu_id` int(11) NOT NULL,
  `sub_id` varchar(12) NOT NULL,
  `sub_mark` int(3) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ugstudentsubject`
--

INSERT INTO `ugstudentsubject` (`stu_id`, `sub_id`, `sub_mark`) VALUES
(12, 'CS/1102', 100),
(12, 'CS/1103', 98),
(12, 'CS/1105', 90),
(14, 'BMHRM/1101', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ugsubject`
--

CREATE TABLE `ugsubject` (
  `sub_id` varchar(12) NOT NULL,
  `sub_name` varchar(50) NOT NULL,
  `credit` int(1) NOT NULL,
  `price` int(5) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ugsubject`
--

INSERT INTO `ugsubject` (`sub_id`, `sub_name`, `credit`, `price`, `course_id`) VALUES
('BMHRM/1101', 'Management Process', 3, 3200, 4),
('BMHRM/1102', 'Business Mathematics and Statistics', 3, 3500, 4),
('CS/1101', 'Mathematics I', 3, 2500, 1),
('CS/1102', 'Introduction to Computer Science', 3, 3500, 1),
('CS/1103', 'Programming in C', 3, 3500, 1),
('CS/1104', 'Professional Development', 3, 3500, 1),
('CS/1105', 'Algorithms and Data structures', 3, 4000, 1),
('CS/1201', 'Object Oriented Programming with Java', 3, 3500, 1),
('CS/1202', 'Database Management Systems', 3, 3800, 1),
('CS/1203', 'Computer Architecture', 3, 3500, 1),
('CS/1204', 'Data communications and networks', 3, 3000, 1),
('CS/1205', 'Web Based Application Development', 3, 3500, 1),
('SE/1101', 'Professional Development', 3, 3500, 2),
('SE/1102', 'Web Based Application Development', 3, 2500, 2),
('SE/1201', 'Human Computer Interaction', 3, 2500, 2),
('SE/1202', 'Business Processes and ERP', 3, 2500, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`int_id`);

--
-- Indexes for table `instructorlab`
--
ALTER TABLE `instructorlab`
  ADD PRIMARY KEY (`int_id`,`lab_id`),
  ADD KEY `lab_id` (`lab_id`);

--
-- Indexes for table `instructorsubject`
--
ALTER TABLE `instructorsubject`
  ADD PRIMARY KEY (`sub_id`,`int_id`),
  ADD KEY `int_id` (`int_id`);

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`lab_id`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`lec_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `ugcourse`
--
ALTER TABLE `ugcourse`
  ADD PRIMARY KEY (`course_id`),
  ADD UNIQUE KEY `course_name` (`course_name`);

--
-- Indexes for table `uglogin`
--
ALTER TABLE `uglogin`
  ADD PRIMARY KEY (`username`),
  ADD KEY `uglogin_ibfk_1` (`stu_id`);

--
-- Indexes for table `ugstudent`
--
ALTER TABLE `ugstudent`
  ADD PRIMARY KEY (`stu_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `ugstudentsubject`
--
ALTER TABLE `ugstudentsubject`
  ADD PRIMARY KEY (`stu_id`,`sub_id`),
  ADD KEY `sub_id` (`sub_id`);

--
-- Indexes for table `ugsubject`
--
ALTER TABLE `ugsubject`
  ADD PRIMARY KEY (`sub_id`),
  ADD KEY `course_id` (`course_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `int_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `lec_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ugcourse`
--
ALTER TABLE `ugcourse`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ugstudent`
--
ALTER TABLE `ugstudent`
  MODIFY `stu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `instructorlab`
--
ALTER TABLE `instructorlab`
  ADD CONSTRAINT `instructorlab_ibfk_1` FOREIGN KEY (`int_id`) REFERENCES `instructor` (`int_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `instructorlab_ibfk_2` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`lab_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `instructorsubject`
--
ALTER TABLE `instructorsubject`
  ADD CONSTRAINT `instructorsubject_ibfk_1` FOREIGN KEY (`sub_id`) REFERENCES `ugsubject` (`sub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `instructorsubject_ibfk_2` FOREIGN KEY (`int_id`) REFERENCES `instructor` (`int_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `uglogin`
--
ALTER TABLE `uglogin`
  ADD CONSTRAINT `uglogin_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `ugstudent` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ugstudent`
--
ALTER TABLE `ugstudent`
  ADD CONSTRAINT `ugstudent_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `ugcourse` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ugstudentsubject`
--
ALTER TABLE `ugstudentsubject`
  ADD CONSTRAINT `ugstudentsubject_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `ugstudent` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ugstudentsubject_ibfk_2` FOREIGN KEY (`sub_id`) REFERENCES `ugsubject` (`sub_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ugsubject`
--
ALTER TABLE `ugsubject`
  ADD CONSTRAINT `ugsubject_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `ugcourse` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
