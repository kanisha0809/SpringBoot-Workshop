package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Getting student by id
    public Student getStudentById(Long id) {
    	return studentRepository.findById(id).get();
    }

    // Save a new student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update student details
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setAge(updatedStudent.getAge());
            
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }

    // Delete a student by ID
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
        	studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }
}
