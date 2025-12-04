DROP DATABASE IF EXISTS final_project_db;
CREATE DATABASE IF NOT EXISTS final_project_db;

USE final_project_db;

CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

INSERT INTO student (name, email, course, country) VALUES
('Alice Johnson', 'EMAIL', 'Mathematics', 'USA'),
('Bob Smith', 'EMAIL', 'Computer Science', 'Canada'),
('Charlie Brown', 'EMAIL', 'Physics', 'UK');
('Diana Prince', 'EMAIL', 'Chemistry', 'Australia'),
('Ethan Hunt', 'EMAIL', 'Biology', 'New Zealand');