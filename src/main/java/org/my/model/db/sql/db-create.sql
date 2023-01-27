/*DROP database IF EXISTS admissionCommittee;

CREATE database admissionCommittee;*/

DROP TABLE applicant_faculty;
DROP TABLE faculties;
DROP TABLE users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(50),

    name VARCHAR(50),
    email VARCHAR(50),
    city VARCHAR(50),
    region VARCHAR(50),
    educational_institution VARCHAR(50)
);

CREATE TABLE faculties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE,
    budget_places INTEGER,
    total_places INTEGER
);

CREATE TABLE applicant_faculty (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users ON DELETE CASCADE,
    faculty_id INTEGER REFERENCES faculties ON DELETE CASCADE,
    mark1 INTEGER,
    mark2 INTEGER,
    mark3 INTEGER,
    avg_certificate_mark REAL,
    passed BOOLEAN
);

INSERT INTO users VALUES (DEFAULT, 'login1', 'password1', 'APPLICANT',
                          'user1', 'email1@gmail.com', 'city1', 'region1', 'institution1');
INSERT INTO users VALUES (DEFAULT, 'login2', 'password2', 'APPLICANT',
                          'user2', 'email2@gmail.com', 'city2', 'region2', 'institution2');
INSERT INTO users VALUES (DEFAULT, 'login3', 'password3', 'APPLICANT',
                          'user3', 'email3@gmail.com', 'city3', 'region3', 'institution3');
INSERT INTO users VALUES (DEFAULT, 'admin', '1111', 'ADMIN',
                          'admin1', 'admin_email1@gmail.com', 'admin_city1', 'admin_region1', 'admin_institution1');
INSERT INTO faculties VALUES (DEFAULT, 'faculty1', 10, 100);
INSERT INTO faculties VALUES (DEFAULT, 'faculty2', 20, 200);
INSERT INTO faculties VALUES (DEFAULT, 'faculty3', 30, 300);

INSERT INTO applicant_faculty VALUES (DEFAULT, 1, 1, 200, 200, 200, 10.75, 'false');
INSERT INTO applicant_faculty VALUES (DEFAULT, 2, 2, 150, 150, 150, 8.9, 'false');
INSERT INTO applicant_faculty VALUES (DEFAULT, 3, 3, 175, 150, 200, 11.8, 'false');

SELECT * FROM users;
SELECT * FROM faculties;
SELECT * FROM applicant_faculty;
