package com.example.studentcourse.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Email must be unique. Please use a different email address."
        );

        String studentId = request.getParameter("id");
        if (studentId != null && !studentId.isBlank()) {
            return "redirect:/students/edit/" + studentId;
        }
        return "redirect:/students/new";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        return "redirect:/students";
    }
}
