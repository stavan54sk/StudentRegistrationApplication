package com.testpackage.controller;

import com.testpackage.model.StudentEntity;
import com.testpackage.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginForm() {
        return "loginForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitLoginForm(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        String view = "errorForm";
        String errorMessage = "Your Security Credentials do not Match ...!!!,Please Try Again with Valid Security Credentials";
        model.addAttribute("errorMessage", errorMessage);
        try {
            List<StudentEntity> students = studentService.getStudents();
            boolean isStudentPresent = students.stream().anyMatch(studentEntity -> {
                return studentEntity.getUsername().equals(username) && studentEntity.getPassword().equals(password);
            });
            if (isStudentPresent) {
                model.addAttribute("students", students);
                view = "home";
            }
        } catch (Exception exception) {
        } finally {
            return view;
        }
    }

}

