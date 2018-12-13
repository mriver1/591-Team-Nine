/**
* admins
*/
drop table if exists admins;
create table admins (
id int(11) not null primary key auto_increment,
username varchar(40) not null unique,
password varchar(40) not null
) ;


/**
* students
*/
drop table if exists students;
create table students (
BUID varchar(20) not null unique,
firstname VARCHAR(255),
lastname VARCHAR(255),
major VARCHAR(11),
status tinyint(1) not null,
total DOUBLE,
letterGrade CHAR(11),
id int(11) not null primary key auto_increment
) ;


/**
* course
*/
drop table if exists course;
create table course(
classid varchar(40) not null,
classname varchar(255) not null,
classyear varchar(40) not null,
classterm varchar(40) not null,
classcredit FLOAT not null,
uniqueid int(11) not null primary key auto_increment
);


/**
* assignment
*/
DROP TABLE if EXISTS assignment;
CREATE TABLE assignment (
assignname VARCHAR(255) NOT NULL,
weightg DOUBLE NOT NULL,
weightu DOUBLE NOT NULL,
category VARCHAR(255) NOT NULL,
comments VARCHAR(255),
assignid INT(11) NOT NULL PRIMARY KEY auto_increment, 
classid INT(11) NOT NULL,
UNIQUE(assignname, classid),
FOREIGN KEY(classid) REFERENCES course(uniqueid) ON DELETE CASCADE
);



/**
* enrollments
*/
DROP TABLE if EXISTS enrollments;
CREATE TABLE enrollments(
id INT(11) NOT NULL PRIMARY KEY auto_increment,
studentID INT(11) NOT NULL ,
classID INT (11) NOT NULL ,
foreign key (studentID) references students(id) on delete cascade on update cascade,
foreign key (classID) references course(uniqueID) on delete cascade on update cascade
)


/**
* grades
*/
DROP TABLE if EXISTS grades;
CREATE TABLE grades (
id INT(11) NOT NULL PRIMARY KEY auto_increment,
studentID INT(11) NOT NULL, 
classid INT(11) NOT NULL,
assignid INT(11) NOT NULL,
comments VARCHAR(255),
grade DOUBLE NOT NULL,

FOREIGN KEY(studentID) REFERENCES students(id) ON DELETE CASCADE,
FOREIGN KEY(classid) REFERENCES course(uniqueid) ON DELETE CASCADE,
FOREIGN KEY(assignid) REFERENCES assignment(assignid) ON DELETE CASCADE,
UNIQUE(studentID, classid)
);












