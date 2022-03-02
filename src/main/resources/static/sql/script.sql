
CREATE DATABASE IF NOT EXISTS docdrive;
USE docdrive;
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(255) NOT NULL,
    lname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR (255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS classroom;
CREATE TABLE classroom (
    classroom_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    classroomname VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
      FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS lesson;
CREATE TABLE lesson (
    lesson_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    lessonname VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
      FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    classroom_id INTEGER NOT NULL,
      FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--test the relation between the three tables