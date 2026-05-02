INSERT INTO courses (id, title, description) VALUES
(1, 'Java Fundamentals', 'Core Java syntax, object-oriented programming, and collections.'),
(2, 'Spring Boot MVC', 'Build web applications using Spring MVC, controllers, and views.'),
(3, 'Spring Data JPA', 'Persist and query relational data using repositories and Hibernate.'),
(4, 'MySQL Essentials', 'Design relational schemas and write practical SQL queries.'),
(5, 'REST API Design', 'Create clean HTTP APIs with validation and error handling.'),
(6, 'Frontend Basics', 'HTML, CSS, JavaScript, and responsive user interfaces.'),
(7, 'Unit Testing', 'Write reliable tests using JUnit and Mockito.'),
(8, 'Microservices Basics', 'Understand service boundaries, configuration, and communication.'),
(9, 'Security Basics', 'Authentication, authorization, and secure coding principles.'),
(10, 'Cloud Deployment', 'Package and deploy Spring Boot applications to cloud platforms.');

INSERT INTO students (id, name, email) VALUES
(1, 'Aarav Sharma', 'aarav.sharma@example.com'),
(2, 'Diya Patel', 'diya.patel@example.com'),
(3, 'Vihaan Reddy', 'vihaan.reddy@example.com'),
(4, 'Ananya Singh', 'ananya.singh@example.com'),
(5, 'Ishaan Gupta', 'ishaan.gupta@example.com'),
(6, 'Meera Nair', 'meera.nair@example.com'),
(7, 'Kabir Khan', 'kabir.khan@example.com'),
(8, 'Sara Thomas', 'sara.thomas@example.com'),
(9, 'Rohan Mehta', 'rohan.mehta@example.com'),
(10, 'Nisha Verma', 'nisha.verma@example.com');

INSERT INTO student_courses (student_id, course_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 4), (2, 6),
(3, 1), (3, 5), (3, 7),
(4, 3), (4, 4), (4, 8),
(5, 2), (5, 7), (5, 9),
(6, 1), (6, 6), (6, 10),
(7, 3), (7, 5), (7, 8),
(8, 4), (8, 7), (8, 10),
(9, 1), (9, 2), (9, 9),
(10, 5), (10, 6), (10, 10);
