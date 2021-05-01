package com.testpackage.controller;

import com.testpackage.model.StudentEntity;
import com.testpackage.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    StudentService studentService;

    public RegistrationController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "registrationForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitRegistrationForm(Model model, @ModelAttribute("student") StudentEntity student) {
        String view = "errorForm";
        String errorMessage = "Your Username Already Exists ...!!!,Please try to Login or Register with a New Username";
        model.addAttribute("errorMessage", errorMessage);
        try {
            if (studentService.createStudent(student) != null) {
                view = "registrationConfirmationForm";
            }
        } catch (Exception exception) {
            model.addAttribute("errorMessage", errorMessage);
        } finally {
            return view;
        }
    }

}
