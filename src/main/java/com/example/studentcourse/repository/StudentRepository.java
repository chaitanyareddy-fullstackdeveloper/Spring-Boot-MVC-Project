package com.example.studentcourse.repository;

import com.example.studentcourse.dto.StudentCourseView;
import com.example.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Student> findByEmail(String email);

    @EntityGraph(attributePaths = "courses")
    @Query("SELECT DISTINCT s FROM Student s ORDER BY s.name")
    List<Student> findAllWithCourses();

    @EntityGraph(attributePaths = "courses")
    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Long id);

    @Query("SELECT s.name AS studentName, c.title AS courseTitle FROM Student s JOIN s.courses c ORDER BY s.name, c.title")
    List<StudentCourseView> findStudentCoursePairs();
}
