package com.example.studentcourse.service;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Set<Course> getCoursesByIds(Set<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) {
            return new LinkedHashSet<>();
        }

        List<Course> courses = courseRepository.findAllById(courseIds);
        if (courses.size() != courseIds.size()) {
            throw new ResourceNotFoundException("One or more selected courses could not be found.");
        }
        return new LinkedHashSet<>(courses);
    }
}
