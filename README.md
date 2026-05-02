# Student Course MVC

This is a Spring Boot MVC application using JSP, Spring Data JPA, Hibernate, MySQL, Maven, JUnit, and Mockito.

## Architecture

The application follows a simple layered MVC structure:

- `entity`: JPA entities for `Student` and `Course`
- `repository`: Spring Data JPA repositories and JPQL join query
- `service`: business logic, duplicate-email checks, and course enrollment handling
- `controller`: Spring MVC request handling and form binding
- `exception`: global exception handling for database and not-found errors
- `src/main/webapp/WEB-INF/jsp`: JSP views using JSTL, EL, and Spring form tags

## Project Structure

```text
student-course-mvc/
|-- pom.xml
|-- README.md
|-- src/
|   |-- main/
|   |   |-- java/com/example/studentcourse/
|   |   |   |-- StudentCourseApplication.java
|   |   |   |-- config/WebConfig.java
|   |   |   |-- controller/HomeController.java
|   |   |   |-- controller/StudentController.java
|   |   |   |-- dto/StudentCourseView.java
|   |   |   |-- dto/StudentForm.java
|   |   |   |-- entity/Course.java
|   |   |   |-- entity/Student.java
|   |   |   |-- exception/DuplicateEmailException.java
|   |   |   |-- exception/GlobalExceptionHandler.java
|   |   |   |-- exception/ResourceNotFoundException.java
|   |   |   |-- repository/CourseRepository.java
|   |   |   |-- repository/StudentRepository.java
|   |   |   |-- service/CourseService.java
|   |   |   `-- service/StudentService.java
|   |   |-- resources/
|   |   |   |-- application.properties
|   |   |   `-- data.sql
|   |   `-- webapp/WEB-INF/jsp/
|   |       |-- add-student.jsp
|   |       |-- edit-student.jsp
|   |       `-- students.jsp
|   `-- test/
|       |-- java/com/example/studentcourse/
|       |   |-- repository/StudentRepositoryTest.java
|       |   |-- service/CourseServiceTest.java
|       |   `-- service/StudentServiceTest.java
|       `-- resources/application-test.properties
```

## MySQL Setup

Make sure MySQL is running, then use either the default connection values or environment variables.

Default configuration:

```properties
DB_URL=jdbc:mysql://localhost:3306/student_course_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=
```

The application creates the schema and loads `data.sql` on startup for the assignment demo.

## Run

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080/students
```

## Test

```bash
mvn test
```

Repository tests use H2 in MySQL compatibility mode. The application itself is configured for MySQL.
