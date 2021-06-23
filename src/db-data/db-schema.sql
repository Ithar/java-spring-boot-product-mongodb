DROP SCHEMA IF EXISTS university_courses;

CREATE SCHEMA university_courses;

USE university_courses;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor_detail`;
DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `course_student`;

-- TABLE: instructor_detail
CREATE TABLE `instructor_detail` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `bio` varchar(128) DEFAULT NULL,
    `website` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- TABLE: instructor
CREATE TABLE `instructor` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    `instructor_detail_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
    REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- TABLE: course
CREATE TABLE `course` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(128) DEFAULT NULL,
    `instructor_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `instructor` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- TABLE: review
CREATE TABLE `review` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `course_id` int(11) DEFAULT NULL,
    `comment` varchar(256) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_COURSE_ID_idx` (`course_id`),
    CONSTRAINT `FK_COURSE`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- TABLE: student
CREATE TABLE `student` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- TABLE: course_student
CREATE TABLE `course_student` (
    `course_id` int(11) NOT NULL,
    `student_id` int(11) NOT NULL,
    PRIMARY KEY (`course_id`,`student_id`),
    KEY `FK_STUDENT_idx` (`student_id`),
    CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
