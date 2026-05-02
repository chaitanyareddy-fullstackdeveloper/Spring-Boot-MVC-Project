package com.example.studentcourse.service;

import com.example.studentcourse.entity.Course;
import com.example.studentcourse.exception.ResourceNotFoundException;
import com.example.studentcourse.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void getCoursesByIdsReturnsCoursesWhenAllIdsExist() {
        Course course = new Course(1L, "Java Fundamentals", "Learn Java basics.");
        when(courseRepository.findAllById(Set.of(1L))).thenReturn(List.of(course));

        Set<Course> courses = courseService.getCoursesByIds(Set.of(1L));

        assertThat(courses).containsExactly(course);
    }

    @Test
    void getCoursesByIdsThrowsWhenAnyCourseIsMissing() {
        when(courseRepository.findAllById(Set.of(1L, 2L))).thenReturn(List.of(new Course(1L, "Java", "Basics")));

        assertThatThrownBy(() -> courseService.getCoursesByIds(Set.of(1L, 2L)))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("One or more selected courses could not be found.");
    }
}
