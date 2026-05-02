package com.example.studentcourse.repository;

import com.example.studentcourse.dto.StudentCourseView;
import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findStudentCoursePairsReturnsJoinedStudentAndCourseTitles() {
        Course java = courseRepository.save(new Course("Java Fundamentals", "Learn Java basics."));
        Course spring = courseRepository.save(new Course("Spring Boot MVC", "Build MVC applications."));

        Student student = new Student("Ananya Singh", "ananya@example.com");
        student.getCourses().add(java);
        student.getCourses().add(spring);
        studentRepository.saveAndFlush(student);

        List<StudentCourseView> rows = studentRepository.findStudentCoursePairs();

        assertThat(rows).hasSize(2);
        assertThat(rows)
                .extracting(row -> row.getStudentName() + " - " + row.getCourseTitle())
                .containsExactlyInAnyOrder(
                        "Ananya Singh - Java Fundamentals",
                        "Ananya Singh - Spring Boot MVC"
                );
    }

    @Test
    void findByIdWithCoursesLoadsAssociatedCourses() {
        Course course = courseRepository.save(new Course("Unit Testing", "JUnit and Mockito basics."));
        Student student = new Student("Kabir Khan", "kabir@example.com");
        student.getCourses().add(course);
        Student savedStudent = studentRepository.saveAndFlush(student);

        Student loadedStudent = studentRepository.findByIdWithCourses(savedStudent.getId()).orElseThrow();

        assertThat(loadedStudent.getCourses()).hasSize(1);
        assertThat(loadedStudent.getCourses().iterator().next().getTitle()).isEqualTo("Unit Testing");
    }
}
