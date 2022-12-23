package com.advanced.java.database.homework.services;

import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.entities.Instructor;
import com.advanced.java.database.homework.entities.Student;
import com.advanced.java.database.homework.repositories.CourseRepository;
import com.advanced.java.database.homework.repositories.InstructorRepository;
import com.advanced.java.database.homework.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    //Student services
    public List<Student> getAllStudents(){
        return (List<Student>) studentRepository.findAll();
    }
    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    //Course services
    public List<Course> getAllCourses(){return (List<Course>) courseRepository.findAll();}
    public Optional<Course> getCourseById(Long id){return courseRepository.findById(id);}
    public Course saveCourse(Course course){return courseRepository.save(course);}
    public void deleteCourse(Long id){courseRepository.deleteById(id);}

    //Instructor services
    public List<Instructor> getAllInstructors(){ return (List<Instructor>) instructorRepository.findAll();}
    public Optional<Instructor> getInstructorById(Long id){return instructorRepository.findById(id);}
    public Instructor saveInstructor (Instructor instructor){return instructorRepository.save(instructor);}
    public void deleteInstructor( Long id){instructorRepository.deleteById(id);}

}
