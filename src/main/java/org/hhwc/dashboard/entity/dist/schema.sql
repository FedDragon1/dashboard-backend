-- Student

CREATE TABLE student(
                        uuid VARCHAR(32) PRIMARY KEY,
                        name VARCHAR(32) NOT NULL,
                        gender VARCHAR(12) NOT NULL,
                        birthday DATE NOT NULL
);

INSERT INTO student values ('1', '学生张三', '男', '2009-01-20');
INSERT INTO student values ('2', '学生李四', '女', '2005-01-20');
INSERT INTO student values ('3', '学生王五', '男', '2009-01-20');
INSERT INTO student values ('4', '学生赵六', '男', '2009-10-02');

DELETE FROM student;
DROP TABLE student;

-- Instructor

CREATE TABLE instructor(
                           uuid VARCHAR(32) PRIMARY KEY,
                           name VARCHAR(32) NOT NULL,
                           password VARCHAR(32) NOT NULL
);

INSERT INTO instructor values ('1', '教师张三', 'zhangsan123');
INSERT INTO instructor values ('2', '教师李四', 'lisi123');
INSERT INTO instructor values ('3', '教师王五', 'wangwu123');
INSERT INTO instructor values ('4', '教师赵六', 'zhaoliu123');

DELETE FROM instructor;
DROP TABLE instructor;

-- Courses

CREATE TABLE course(
                       uuid VARCHAR(32) PRIMARY KEY,
                       name VARCHAR(32) NOT NULL,
                       created DATE NOT NULL,
                       instructor_uuid VARCHAR(32) NOT NULL
);

INSERT INTO course values ('1', '数学', '2024-02-17', '2');
INSERT INTO course values ('2', '英语', '2021-07-26', '1');
INSERT INTO course values ('3', '语文', '2023-04-29', '4');
INSERT INTO course values ('4', '体育', '2022-09-25', '3');
INSERT INTO course values ('5', '物理', '2020-04-12', '2');
INSERT INTO course values ('6', '化学', '2020-05-03', '3');

DELETE FROM course;
DROP TABLE course;

-- Course Members

CREATE TABLE course_member(
                              student_uuid VARCHAR(32),
                              course_uuid VARCHAR(32),
                              grade DOUBLE NOT NULL,
                              PRIMARY KEY (student_uuid, course_uuid)
);

INSERT INTO course_member values ('1', '1', 0.8);
INSERT INTO course_member values ('1', '4', 0.9);
INSERT INTO course_member values ('1', '3', 0.7);
INSERT INTO course_member values ('2', '2', 0.8);
INSERT INTO course_member values ('2', '5', 0.3);
INSERT INTO course_member values ('3', '6', 0.4);
INSERT INTO course_member values ('4', '5', 0.6);

DELETE FROM course_member;
DROP TABLE course_member;

-- Admin

CREATE TABLE admin(
                      username VARCHAR(32) PRIMARY KEY,
                      password VARCHAR(32)
);

INSERT INTO admin values ('admin', 'root');

DELETE FROM admin;
DROP TABLE admin;

-- Attendance Assignments

CREATE TABLE attendance(
                           uuid VARCHAR(32) PRIMARY KEY,
                           date DATE NOT NULL,
                           course_uuid VARCHAR(32) NOT NULL
);

INSERT INTO attendance values ('1', '2024-02-19', '1');
INSERT INTO attendance values ('2', '2024-02-20', '1');
INSERT INTO attendance values ('3', '2024-02-21', '1');
INSERT INTO attendance values ('4', '2024-02-21', '2');
INSERT INTO attendance values ('5', '2024-02-21', '3');
INSERT INTO attendance values ('6', '2024-02-21', '4');
INSERT INTO attendance values ('7', '2024-02-21', '5');
INSERT INTO attendance values ('8', '2024-02-21', '6');

DELETE FROM attendance;
DROP TABLE attendance;

-- Attendance Records

CREATE TABLE attendance_records(
                                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                   student_uuid VARCHAR(32) NOT NULL,
                                   attendance_uuid VARCHAR(32) NOT NULL
);

INSERT INTO attendance_records(student_uuid, attendance_uuid) values ('1', '1');
INSERT INTO attendance_records(student_uuid, attendance_uuid) values ('1', '6');
INSERT INTO attendance_records(student_uuid, attendance_uuid) values ('2', '2');
INSERT INTO attendance_records(student_uuid, attendance_uuid) values ('2', '7');

DELETE FROM attendance_records;
DROP TABLE attendance_records;
