package com.advanced.java.database.homework.services;

import com.advanced.java.database.homework.entities.Student;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final DatabaseService databaseService;

    @Autowired
    public StudentService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public List<Student> getAllStudents(){
        return databaseService.getAllStudents();
    }
    public Optional<Student> getStudentById(Long id) throws BadEntityRequest {
        if (databaseService.getStudentById(id).isEmpty()){
            throw new BadEntityRequest("The student with the id:\t"+ id + " doesn't exist.(it's empty) CAll failed.");
        }
        return databaseService.getStudentById(id);
    }
    public Student addStudent(Student student) throws BadEntityRequest {
        if (student.getFirstName().isEmpty() || student.getFirstName().isBlank())
            throw new BadEntityRequest( "Students first name is a required field.");
        if (student.getLastName().isEmpty() || student.getLastName().isBlank())
            throw new BadEntityRequest("Students Last name is a required field.");
        if (student.getSsn() == null|| student.getSsn() <= 0)
            throw new BadEntityRequest("students SSN is a required field.");
        if (student.getCreditsEarned() == null|| student.getCreditsEarned() < 0)
            throw new BadEntityRequest("students credits earned is a required field. (can't be less than zero)");
        if (student.getStudentID().isEmpty()|| student.getStudentID().isBlank())
            throw new BadEntityRequest("student ID number is a required field.");
        if (student.getMajor().isEmpty()|| student.getMajor().isBlank())
            throw new BadEntityRequest("student major is a required field.");
        if (student.getGpa() == null|| student.getGpa() < 0 || student.getGpa() > 4.5)
            throw new BadEntityRequest("students GPA is a required field. And must be between 0 and 4.5");
        //check for valid input and trow appropriate error message // also might need to write a unit test for the logic

        return databaseService.saveStudent(student);
    }
    public Student updateStudent(Student student, Long id) throws BadEntityRequest {
        if (databaseService.getStudentById(id).isEmpty()) {
            throw new BadEntityRequest("The Student with id " + id + " doesn't exist.  Update failed.");
        }
        if (student.getFirstName().isEmpty() || student.getFirstName().isBlank())
            throw new BadEntityRequest( "Students first name is a required field.");
        if (student.getLastName().isEmpty() || student.getLastName().isBlank())
            throw new BadEntityRequest("Students Last name is a required field.");
        if (student.getSsn() == null|| student.getSsn() <= 0)
            throw new BadEntityRequest("students SSN is a required field.");
        if (student.getCreditsEarned() == null|| student.getCreditsEarned() < 0)
            throw new BadEntityRequest("students credits earned is a required field. (can't be less than zero)");
        if (student.getStudentID().isEmpty()|| student.getStudentID().isBlank())
            throw new BadEntityRequest("student ID number is a required field.");
        if (student.getMajor().isEmpty()|| student.getMajor().isBlank())
            throw new BadEntityRequest("student major is a required field.");
        if (student.getGpa() == null|| student.getGpa() < 0 || student.getGpa() > 4.5)
            throw new BadEntityRequest("students GPA is a required field. And must be between 0 and 4.5");

        if(student.getId() != id)

            throw new BadEntityRequest("ID can not be updated. " + id + " does not match the id:\t" + student.getId());

        student.setId(id);

        return databaseService.saveStudent(student);
    }
    public void deleteStudent(Long id) throws  BadEntityRequest{
        if (databaseService.getStudentById(id).isEmpty()){
            throw new BadEntityRequest("The student with the id:\t"+ id + " doesn't exist. Deletion failed.");
        }
        databaseService.deleteStudent(id);
    }
}
