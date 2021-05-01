package com.testpackage.controller;

import com.testpackage.model.StudentEntity;
import com.testpackage.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/forgot")
public class ForgotController {

    StudentService studentService;

    public ForgotController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationForm() {
        return "forgotForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitbRegistrationForm(Model model, @RequestParam("username") String username, @RequestParam("securityQuestion") String securityQuestion, @RequestParam("securityAnswer") String securityAnswer) {

        String view = "errorForm";
        String errorMessage = "Your Security Credentials do not Match ...!!!,Please Try Again with Valid Security Credentials";
        try {
            List<StudentEntity> students = studentService.getStudents();
            Optional<StudentEntity> studentEntityOptional = students.stream().filter(studentEntity -> studentEntity.getUsername().equals(username) && studentEntity.getSecurityquestion().equals(securityQuestion) && studentEntity.getSecurityanswer().equals(securityAnswer)).findFirst();
            if (studentEntityOptional.get() != null) {
//                SMSUtility.sendSMSToStudent(studentEntityOptional.get());
                model.addAttribute("student", studentEntityOptional.get());
                view = "forgotConfirmationForm";
            }
        } catch (Exception exception) {
            model.addAttribute("errorMessage", errorMessage);
        } finally {
            return view;
        }
    }

}