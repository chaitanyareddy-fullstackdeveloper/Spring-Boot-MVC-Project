package com.example.studentcourse.service;

import com.example.studentcourse.dto.StudentForm;
import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.exception.DuplicateEmailException;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private StudentService studentService;

    @Test
    void createStudentSavesStudentWithSelectedCourses() {
        StudentForm form = new StudentForm();
        form.setName("  Diya Patel  ");
        form.setEmail("  Diya.Patel@Example.com  ");
        form.setCourseIds(Set.of(1L));

        Course course = new Course(1L, "Spring Boot MVC", "Build MVC applications.");
        when(studentRepository.existsByEmail("diya.patel@example.com")).thenReturn(false);
        when(courseService.getCoursesByIds(Set.of(1L))).thenReturn(Set.of(course));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        studentService.createStudent(form);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentCaptor.capture());

        Student savedStudent = studentCaptor.getValue();
        assertThat(savedStudent.getName()).isEqualTo("Diya Patel");
        assertThat(savedStudent.getEmail()).isEqualTo("diya.patel@example.com");
        assertThat(savedStudent.getCourses()).containsExactly(course);
    }

    @Test
    void createStudentThrowsWhenEmailAlreadyExists() {
        StudentForm form = new StudentForm();
        form.setName("Aarav Sharma");
        form.setEmail("aarav@example.com");

        when(studentRepository.existsByEmail("aarav@example.com")).thenReturn(true);

        assertThatThrownBy(() -> studentService.createStudent(form))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessage("A student with email 'aarav@example.com' already exists.");

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void updateStudentUpdatesExistingStudent() {
        StudentForm form = new StudentForm();
        form.setId(7L);
        form.setName("Kabir Khan");
        form.setEmail("kabir.updated@example.com");
        form.setCourseIds(Set.of(2L));

        Student existingStudent = new Student(7L, "Kabir", "kabir@example.com");
        Course course = new Course(2L, "MySQL Essentials", "SQL basics.");

        when(studentRepository.findByIdWithCourses(7L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.existsByEmailAndIdNot("kabir.updated@example.com", 7L)).thenReturn(false);
        when(courseService.getCoursesByIds(Set.of(2L))).thenReturn(Set.of(course));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        Student updatedStudent = studentService.updateStudent(form);

        assertThat(updatedStudent.getName()).isEqualTo("Kabir Khan");
        assertThat(updatedStudent.getEmail()).isEqualTo("kabir.updated@example.com");
        assertThat(updatedStudent.getCourses()).containsExactly(course);
    }

    @Test
    void getStudentWithCoursesThrowsWhenStudentDoesNotExist() {
        when(studentRepository.findByIdWithCourses(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentWithCourses(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Student not found with id: 99");
    }
}
