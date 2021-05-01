package com.testpackage.controller;

import com.testpackage.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/display")
public class DisplayController {

    StudentService studentService;

    public DisplayController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String displayStudent(@PathVariable("id") Long id, Model model) {

        model.addAttribute("student", studentService.getStudent(id));
        return "displayForm";
    }


}

