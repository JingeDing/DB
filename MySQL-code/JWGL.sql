CREATE TABLE department(
	dno CHAR(2) PRIMARY KEY,
	dname VARCHAR(32) NOT NULL
)ENGINE=INNODB DEFAULT charset=utf8;

CREATE TABLE `class`(
	`cno` 	CHAR(8) PRIMARY KEY,
	`cname` VARCHAR(32),
	`year`  INT(4),
	`cyear` INT(4),
	`major` VARCHAR(32),
	`dno`   CHAR(2),
	FOREIGN KEY(`dno`) REFERENCES department(dno) ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=INNODB DEFAULT charset=utf8;

CREATE TABLE `teacher`(
	`tno`   CHAR(5) PRIMARY KEY,
	`tname` VARCHAR(32),
	`title` VARCHAR(32),
	`email` VARCHAR(32),
	`dno`   CHAR(2),
	FOREIGN KEY(`dno`) REFERENCES department(dno) ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=INNODB DEFAULT charset=utf8;

CREATE TABLE `student`(
	`sno`   CHAR(12) PRIMARY KEY,
	`sname` VARCHAR(32) NOT NULL,
	`sex`   ENUM('男','女')   DEFAULT NULL,
	`cno`		CHAR(6),
	FOREIGN KEY(`cno`) REFERENCES class(`cno`) ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=INNODB DEFAULT charset=utf8;

CREATE TABLE `course`(
	`no`     		CHAR(12) PRIMARY KEY,
	`name`   		VARCHAR(32) ,
	`credit` 		INT(4),
	`semester`  CHAR(5),
	`tno`    		CHAR(5),
	`weekday`   INT(2),
	`ctime`     int(2),
	FOREIGN KEY(`tno`) REFERENCES teacher(tno) ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=INNODB DEFAULT charset=utf8;

CREATE TABLE `sc`(
	`sno` CHAR(12),
	`no`  CHAR(12),
	`grade` INT(3),
	`gpa`   FLOAT(5,4),
	`weekday`   CHAR(2),
	`ctime`     CHAR(10),
	`semester`  CHAR(5),
	PRIMARY KEY(`sno`,`no`),
	FOREIGN KEY(`sno`) REFERENCES student(`sno`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(`no`) REFERENCES course(`no`)  ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE KEY `UK_xk`(`sno`,`weekday`,`ctime`,`semester`)
)ENGINE=INNODB DEFAULT charset=utf8;





