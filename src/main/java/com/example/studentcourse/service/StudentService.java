package com.example.studentcourse.service;

import com.example.studentcourse.dto.StudentCourseView;
import com.example.studentcourse.dto.StudentForm;
import com.example.studentcourse.entity.Course;
import com.example.studentcourse.entity.Student;
import com.example.studentcourse.exception.DuplicateEmailException;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Transactional
    public Student createStudent(StudentForm form) {
        String normalizedEmail = normalizeEmail(form.getEmail());
        if (studentRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateEmailException(normalizedEmail);
        }

        Student student = new Student();
        applyForm(student, form);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllWithCourses();
    }

    @Transactional
    public Student updateStudent(StudentForm form) {
        Student student = studentRepository.findByIdWithCourses(form.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + form.getId()));

        String normalizedEmail = normalizeEmail(form.getEmail());
        if (studentRepository.existsByEmailAndIdNot(normalizedEmail, form.getId())) {
            throw new DuplicateEmailException(normalizedEmail);
        }

        applyForm(student, form);
        return studentRepository.save(student);
    }

    public Student getStudentWithCourses(Long id) {
        return studentRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public List<StudentCourseView> getStudentCoursePairs() {
        return studentRepository.findStudentCoursePairs();
    }

    public StudentForm toForm(Student student) {
        StudentForm form = new StudentForm();
        form.setId(student.getId());
        form.setName(student.getName());
        form.setEmail(student.getEmail());
        form.setCourseIds(student.getCourses().stream().map(Course::getId).collect(java.util.stream.Collectors.toSet()));
        return form;
    }

    private void applyForm(Student student, StudentForm form) {
        Set<Course> selectedCourses = courseService.getCoursesByIds(form.getCourseIds());
        student.setName(form.getName().trim());
        student.setEmail(normalizeEmail(form.getEmail()));
        student.enrollInCourses(selectedCourses);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
