package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentForm;
import com.example.studentcourse.exception.DuplicateEmailException;
import com.example.studentcourse.service.CourseService;
import com.example.studentcourse.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("studentCoursePairs", studentService.getStudentCoursePairs());
        return "students";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("studentForm")) {
            model.addAttribute("studentForm", new StudentForm());
        }
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-student";
    }

    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        }

        try {
            studentService.createStudent(studentForm);
        } catch (DuplicateEmailException exception) {
            bindingResult.rejectValue("email", "student.email.duplicate", exception.getMessage());
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Student created successfully.");
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        if (!model.containsAttribute("studentForm")) {
            model.addAttribute("studentForm", studentService.toForm(studentService.getStudentWithCourses(id)));
        }
        model.addAttribute("courses", courseService.getAllCourses());
        return "edit-student";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("studentForm") StudentForm studentForm,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.getAllCourses());
            return "edit-student";
        }

        try {
            studentService.updateStudent(studentForm);
        } catch (DuplicateEmailException exception) {
            bindingResult.rejectValue("email", "student.email.duplicate", exception.getMessage());
            model.addAttribute("courses", courseService.getAllCourses());
            return "edit-student";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully.");
        return "redirect:/students";
    }
}
