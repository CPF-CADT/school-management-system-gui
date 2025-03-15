CREATE DATABASE IF NOT EXISTS school;
use school;
show tables;
-- drop database school;
CREATE TABLE IF NOT EXISTS User (
    id VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    dob DATE,
    address VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS Students (
    user_id VARCHAR(10) PRIMARY KEY,
    status BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES User(id) ON UPDATE CASCADE  ON DELETE CASCADE
    -- Additional attributes for Student can go here
);
select * from Students;

CREATE TABLE IF NOT EXISTS Teachers (
    user_id VARCHAR(10) PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_major VARCHAR(20),
    status BOOLEAN
);

CREATE TABLE IF NOT EXISTS Admin (
    user_id VARCHAR(10) PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE,
    role VARCHAR(20),
    status BOOLEAN
);

create table if not exists Course(
	short_name varchar(5) primary key,
	name varchar(50) not null,
	level varchar(20)not null,
	fee int not null,
	description varchar (70)
);

CREATE TABLE IF NOT EXISTS Course_instance (
    year INT NOT NULL,
    term INT NOT NULL,
    group_s VARCHAR(10) NOT NULL,
    short_name VARCHAR(5) NOT NULL,
    course_instance_id VARCHAR(20) GENERATED ALWAYS AS (CONCAT(year, '-', term, '-', group_s, '-', short_name)) STORED,
    teacher_id VARCHAR(5),
    PRIMARY KEY (year, term, group_s, short_name),
    UNIQUE (course_instance_id)  -- Ensure this is unique to link to the Enrollment table
);

CREATE TABLE IF NOT EXISTS Enrollment (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    enroll_date DATE NOT NULL,
    class_id VARCHAR(15) NOT NULL,
    student_id VARCHAR(10),
    course_instance_id VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Quizz (
    id INT PRIMARY KEY,
    time_limit TIME,
    title VARCHAR(25) NOT NULL,
    description VARCHAR(70) NOT NULL,
    instance_id VARCHAR(20) NOT NULL,
    due DATE
);

CREATE TABLE IF NOT EXISTS Assignment (
    id INT PRIMARY KEY,
    instance_id VARCHAR(20),
    instruction VARCHAR(70),
    description VARCHAR(70),
    due DATE,
    title VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS Question (
    id INT,
    quizz_id INT,
    question VARCHAR(70),
    mark INT,
    correct_ans VARCHAR(70),
    options JSON,
    PRIMARY KEY (id, quizz_id)
);

ALTER TABLE Enrollment 
	ADD FOREIGN KEY (student_id) 
	REFERENCES Students(user_id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;

ALTER TABLE Enrollment 
	ADD CONSTRAINT fk_course_instance_id 
	FOREIGN KEY (course_instance_id) 
	REFERENCES Course_instance(course_instance_id)
	ON UPDATE CASCADE 
	ON DELETE CASCADE;

ALTER TABLE Course_instance 
	ADD FOREIGN KEY (teacher_id) 
	REFERENCES Teachers(user_id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;
-- interutp
ALTER TABLE Course_instance 
	ADD FOREIGN KEY (short_name) 
	REFERENCES Course(short_name) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;


ALTER TABLE Quizz 
	ADD FOREIGN KEY (instance_id) 
	REFERENCES Course_instance(course_instance_id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;
ALTER TABLE Assignment 
	ADD FOREIGN KEY (instance_id) 
	REFERENCES Course_instance(course_instance_id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;
ALTER TABLE Question 
	ADD FOREIGN KEY (quizz_id) 
	REFERENCES Quizz(id) 
	ON UPDATE CASCADE 
	ON DELETE CASCADE;

INSERT INTO User (id, first_name, last_name, dob, address, email, phone_number, password) VALUES
('S1', 'John', 'Doe', '2000-01-15', '123 Main St', 'john.doe@stu.kdc.edu', '555-1237', 'password123'),
('S2', 'Jane', 'Smith', '1999-02-20', '456 Elm St', 'jane.smith@stu.kdc.edu', '555-5678', 'password456'),
('S3', 'Alice', 'Johnson', '2001-03-25', '789 Oak St', 'alice.johnson@stu.kdc.edu', '555-8765', 'password789'),
('S4', 'Bob', 'Brown', '2002-04-30', '321 Pine St', 'bob.brown@stu.kdc.edu', '555-4321', 'password101'),
('S5', 'Charlie', 'Davis', '2000-05-05', '654 Maple St', 'charlie.davis@stu.kdc.edu', '555-1357', 'password202'),
('S7', 'Eva', 'Garcia', '2003-07-15', '159 Birch St', 'eva.garcia@stu.kdc.edu', '555-3691', 'password404'),
('S9', 'Grace', 'Lopez', '1999-09-25', '951 Willow St', 'grace.lopez@stu.kdc.edu', '555-1472', 'password606'),
('S10', 'Henry', 'Hernandez', '2002-10-30', '357 Fir St', 'henry.hernandez@stu.kdc.edu', '555-9630', 'password707');

INSERT INTO User (id, first_name, last_name, dob, address, email, phone_number, password) VALUES
('T001', 'Alice', 'Smith', '1985-01-15', '123 Main St', 'alice.smith@tch.kdc.edu', '555-1111', 'password123'),
('T002', 'Bob', 'Johnson', '1978-02-20', '456 Elm St', 'bob.johnson@tch.kdc.edu', '555-2222', 'password456'),
('T003', 'Charlie', 'Williams', '1990-03-25', '789 Oak St', 'charlie.williams@tch.kdc.edu', '555-3333', 'password789'),
('T004', 'David', 'Brown', '1982-04-30', '321 Pine St', 'david.brown@tch.kdc.edu', '555-4444', 'password101'),
('T005', 'Eva', 'Jones', '1995-05-05', '654 Maple St', 'eva.jones@tch.kdc.edu', '555-5555', 'password202'),
('T006', 'Frank', 'Garcia', '1988-06-10', '987 Cedar St', 'frank.garcia@tch.kdc.edu', '555-6666', 'password303'),
('T007', 'Grace', 'Martinez', '1992-07-15', '159 Birch St', 'grace.martinez@tch.kdc.edu', '555-7777', 'password404'),
('T008', 'Henry', 'Hernandez', '1980-08-20', '753 Spruce St', 'henry.hernandez@tch.kdc.edu', '555-8888', 'password505'),
('T009', 'Isabella', 'Lopez', '1993-09-25', '951 Willow St', 'isabella.lopez@tch.kdc.edu', '555-9999', 'password606'),
('T010', 'Jack', 'Wilson', '1987-10-30', '357 Fir St', 'jack.wilson@tch.kdc.edu', '555-0000', 'password707');

INSERT INTO Teachers (user_id, role_major,status) VALUES
('T001', 'Computer Science',TRUE),
('T002', 'Mathematics',TRUE),
('T003', 'Physics',TRUE),
('T004', 'Engineering',TRUE),
('T005', 'Biology',TRUE),
('T006', 'Chemistry',TRUE),
('T007', 'Literature',TRUE),
('T008', 'History',TRUE),
('T009', 'Philosophy',TRUE),
('T010', 'Sociology',TRUE);	
INSERT INTO Students (user_id,status) VALUES
    ('S1',TRUE),
    ('S2',TRUE),
    ('S3',TRUE),
    ('S4',TRUE),
    ('S5',TRUE),
    ('S7',TRUE),
    ('S9',TRUE),
    ('S10',TRUE);

INSERT INTO Course (short_name, name, level, fee, description) VALUES
('MTH', 'Mathematics', 'Beginner', 300, 'Introduction to basic mathematics concepts.'),
('PHY', 'Physics', 'Intermediate', 400, 'Understanding the principles of physics.'),
('CHE', 'Chemistry', 'Advanced', 500, 'In-depth study of chemical reactions and properties.'),
('BIO', 'Biology', 'Beginner', 350, 'Basics of biological sciences and life forms.'),
('CSE', 'Computer Science', 'Intermediate', 450, 'Fundamentals of programming and algorithms.'),
('HIS', 'History', 'Advanced', 400, 'Exploration of significant historical events.'),
('ART', 'Art', 'Beginner', 250, 'Introduction to various art forms and techniques.'),
('MUS', 'Music', 'Intermediate', 300, 'Understanding music theory and practice.'),
('PE', 'Physical Education', 'Beginner', 200, 'Basics of physical fitness and sports.'),
('ECO', 'Economics', 'Advanced', 500, 'Study of economic theories and practices.');

INSERT INTO Course_instance (year, term, group_s, teacher_id, short_name) VALUES
(2022, 1, '1', 'T001', 'MTH'),
(2022, 2, '2', 'T002', 'PHY'),
(2022, 3, '3', 'T003', 'CHE'),
(2023, 1, '1', 'T001', 'BIO'),
(2023, 2, '2', 'T002', 'CSE'),
(2023, 3, '3', 'T003', 'HIS'),
(2024, 1, '1', 'T001', 'ART'),
(2024, 2, '2', 'T002', 'MUS'),
(2024, 3, '3', 'T003', 'PE'),
(2024, 3, '1', 'T001', 'ECO');
select * from Course_instance;
INSERT INTO Enrollment (enroll_date, class_id, student_id, course_instance_id)
VALUES 
    ('2023-01-15', 'C101', 'S1', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND group_s = '1' AND short_name = 'MTH')),
    ('2023-01-16', 'C102', 'S2', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 2 AND group_s = '2' AND short_name = 'PHY')),
    ('2023-01-17', 'C103', 'S3', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 3 AND group_s = '3' AND short_name = 'CHE')),
    ('2023-01-18', 'C101', 'S4', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND group_s = '1' AND short_name = 'MTH')),
    ('2023-01-19', 'C104', 'S5', (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 1 AND group_s = '1' AND short_name = 'BIO')),
    ('2023-01-21', 'C105', 'S7', (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 2 AND group_s = '2' AND short_name = 'CSE')),
    ('2023-01-23', 'C106', 'S9', (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 3 AND group_s = '3' AND short_name = 'HIS')),
    ('2023-01-24', 'C101', 'S10', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND group_s = '1' AND short_name = 'MTH')),
    ('2023-02-01', 'C107', 'S1', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 1 AND group_s = '1' AND short_name = 'ART')),
    ('2023-02-02', 'C108', 'S2', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 2 AND group_s = '2' AND short_name = 'MUS')),
    ('2023-02-03', 'C109', 'S3', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 3 AND group_s = '3' AND short_name = 'PE')),
    ('2023-02-04', 'C110', 'S4', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 3 AND group_s = '1' AND short_name = 'ECO')),
    ('2023-02-05', 'C101', 'S5', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND group_s = '1' AND short_name = 'MTH')),
    ('2023-02-07', 'C103', 'S7', (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 3 AND group_s = '3' AND short_name = 'CHE')),
    ('2023-02-09', 'C105', 'S9', (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 2 AND group_s = '2' AND short_name = 'CSE')),
    ('2023-02-10', 'C106', 'S10', (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 3 AND group_s = '3' AND short_name = 'HIS')),
    ('2023-02-11', 'C107', 'S1', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 1 AND group_s = '1' AND short_name = 'ART')),
    ('2023-02-12', 'C108', 'S2', (SELECT course_instance_id FROM Course_instance WHERE year = 2024 AND term = 2 AND group_s = '2' AND short_name = 'MUS'));
INSERT INTO Quizz (id, time_limit, title, description, instance_id, due) VALUES
(1, '01:00:00', 'Math Quiz 1', 'Basic arithmetic and algebra questions.', 
 (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND short_name = 'MTH'), '2023-01-20'),
(2, '01:30:00', 'Physics Quiz 1', 'Questions on Newtonian mechanics.', 
 (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 2 AND short_name = 'PHY'), '2023-01-21'),
(3, '02:00:00', 'Chemistry Quiz 1', 'Chemical reactions and equations.', 
 (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 3 AND short_name = 'CHE'), '2023-01-22'),
(4, '01:15:00', 'Biology Quiz 1', 'Cell structure and function.', 
 (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 1 AND short_name = 'BIO'), '2023-01-23'),
(5, '01:45:00', 'History Quiz 1', 'Important events in world history.', 
 (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 3 AND short_name = 'HIS'), '2023-01-24');

INSERT INTO Assignment (id, instance_id, instruction, description, due, title) VALUES
(1, (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 1 AND short_name = 'MTH'), 'Complete all questions.', 'Math assignment covering chapters 1-3.', '2023-01-20', 'Math Assignment 1'),
(2, (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 2 AND short_name = 'PHY'), 'Write a report.', 'Physics assignment on energy conservation.', '2023-01-21', 'Physics Assignment 1'),
(3, (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND term = 3 AND short_name = 'CHE'), 'Solve the problems.', 'Chemistry assignment on stoichiometry.', '2023-01-22', 'Chemistry Assignment 1'),
(4, (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 1 AND short_name = 'BIO'), 'Research and present.', 'Biology assignment on cell division.', '2023-01-23', 'Biology Assignment 1'),
(5, (SELECT course_instance_id FROM Course_instance WHERE year = 2023 AND term = 3 AND short_name = 'HIS'), 'Prepare a timeline.', 'History assignment on the World Wars.', '2023-01-24', 'History Assignment 1');

INSERT INTO Question (id, quizz_id, question, mark, correct_ans, options) VALUES
(1, 1, 'What is 2 + 2?', 1, '4', '["1", "2", "3", "4"]'),
(2, 1, 'What is the capital of France?', 1, 'Paris', '["London", "Berlin", "Paris", "Madrid"]'),
(3, 1, 'What is the boiling point of water?', 1, '100°C', '["90°C", "100°C", "110°C", "120°C"]'),
(4, 2, 'What is Newton\'s second law?', 2, 'F=ma', '["F=ma", "E=mc^2", "a^2+b^2=c^2", "v=d/t"]'),
(5, 2, 'What is the chemical symbol for water?', 1, 'H2O', '["O2", "H2O", "CO2", "NaCl"]'),
(6, 3, 'What is the powerhouse of the cell?', 1, 'Mitochondria', '["Nucleus", "Ribosome", "Mitochondria", "Chloroplast"]'),
(7, 3, 'What is the largest planet in our solar system?', 1, 'Jupiter', '["Earth", "Mars", "Jupiter", "Saturn"]'),
(8, 4, 'Who wrote "Hamlet"?', 2, 'Shakespeare', '["Hemingway", "Shakespeare", "Tolkien", "Austen"]'),
(9, 4, 'What is the main ingredient in guacamole?', 1, 'Avocado', '["Tomato", "Avocado", "Onion", "Pepper"]'),
(10, 5, 'What is the currency of Japan?', 1, 'Yen', '["Dollar", "Euro", "Yen", "Won"]')

-- SELECT student_id FROM Enrollment WHERE course_instance_id = (SELECT course_instance_id FROM Course_instance WHERE year = 2022 AND group_s = '1' AND term = 1 AND short_name = 'MTH');	
-- SELECT c.course_instance_id  FROM Course_instance AS c WHERE teacher_id = 'T001';
-- 
-- UPDATE User  
-- SET  
--     first_name = 'new',  
--     last_name = 'new',  
--     dob = '2000-01-01',  
--     address = 'new',  
--     phone_number = 'new',  
--     password_hash = 'newpass'
-- WHERE id = 'id';  
-- 

-- START TRANSACTION;
-- -- Insert into User table
-- INSERT INTO User (id, first_name, last_name, dob, address, email, phone_number, password)
-- VALUES ('S6', 'Nha', 'Jor', '2000-05-15', '123 Main St', 'nha.jor@stu.kdc.edu.', '034431243', 'password123');
-- 
-- -- Insert into Students table
-- INSERT INTO Students (user_id)
-- VALUES ('S6');
-- 
-- COMMIT;	
  
-- select * from  Students;
-- 
--  -- for register teacher
-- 
-- INSERT INTO Teachers (id, first_name, last_name, dob, address, email, phone_number, password, role_major) VALUES
-- ('T005', 'sily', 'joe', '1995-01-15', '123 diet St', 'sily.joe@tch.kdc.edu', '555-1999', 'password182', 'Mathematics');
-- 
-- -- for register student
-- 
-- INSERT INTO Student (id, first_name, last_name, dob, address, email, phone_number, password) VALUES
-- ('S012', 'niga', 'hoe', '2009-01-15', '123 oon St', 'niga.hoe@stu.kdc.edu', '555-1934', 'password934');
-- 
-- -- for get student  data (nha idea) (getStudentCoureEnrolment)
-- select s.*,
-- FROM Student s 
-- JOIN Enrollment e ON e.student_id = s.id
-- JOIN Course_instance c on e.instance_id =c.instance_id 
-- WHERE s.id = 'S1';
-- -- for get student
-- select * from  Student;
-- -- process
-- 
-- SELECT  s.*, c.* FROM Student s JOIN Enrollment e ON s.id = e.student_id  JOIN Course_instance c ON e.instance_id = c.instance_id;
-- 
-- -- 	for get teacher
-- select t.* from Teacher;
-- 
-- -- for get quizz with question
-- select  q.* from Quizz q join  Question qn on q.id=qn.id;
-- 
-- 
-- -- get assignment
-- select a.* from Assignment a;



