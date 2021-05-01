package com.testpackage.service;

import com.testpackage.model.StudentEntity;
import com.testpackage.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository StudentRepository) {
        this.studentRepository = StudentRepository;
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentEntity updateStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentEntity getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<StudentEntity> getStudents() {
        return (List<StudentEntity>) studentRepository.findAll();
    }

}
