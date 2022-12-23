package com.advanced.java.database.homework.controllers;

import com.advanced.java.database.homework.entities.Student;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping(value = "getStudents")
    public List<Student> getStudents(){
        System.out.println("Called getStudents()");
        return studentService.getAllStudents();
    }

    @GetMapping (value = "getStudentById")
    public Optional<Student> getStudentById (@RequestParam Long id) throws BadEntityRequest {
        System.out.println("Called getStudentById with Id: " + id);
        return studentService.getStudentById(id);
    }
    @PostMapping (value = "addStudent")
    public Student addStudent (@RequestBody Student student) throws BadEntityRequest {
        System.out.println("Adding the following new student:\t" + student.toString());
        return studentService.addStudent(student);
    }
    @PatchMapping(value = "updateStudent")
    public Student updateStudent(@RequestBody Student student,
                                 @RequestParam Long id) throws BadEntityRequest {
        System.out.println("Updating the following country: " + student + " with id: " + id);

        return studentService.updateStudent(student, id);
    }
    @DeleteMapping ( value = "deleteStudent")
    public void deleteStudent (@RequestParam Long id) throws BadEntityRequest {
        System.out.println("Deleting student with Id: " + id);
        studentService.deleteStudent(id);
    }
}
