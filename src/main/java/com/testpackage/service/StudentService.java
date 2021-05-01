package com.testpackage.service;

import com.testpackage.model.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity createStudent(StudentEntity student);

    StudentEntity updateStudent(StudentEntity student);

    void deleteStudent(Long id);

    StudentEntity getStudent(Long id);

    List<StudentEntity> getStudents();

}
