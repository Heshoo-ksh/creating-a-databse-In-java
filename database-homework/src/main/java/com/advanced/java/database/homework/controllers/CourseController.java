package com.advanced.java.database.homework.controllers;

import antlr.CharQueue;
import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    CourseController(CourseService courseService){
        this.courseService =  courseService;
    }
    @GetMapping (value ="getAllCourses")
    public List<Course> getAllCourses(){
        System.out.println("Called getAllCourses()");
        return courseService.getAllCourses();
    }
    @GetMapping (value = "getCourseById")
    public Optional<Course> getCourseById(@RequestParam Long id) throws BadEntityRequest {
        System.out.println("Called getCourseById() with id" + id);
        return courseService.getCourseById(id);
    }
    @PostMapping(value = "saveCourse")
    public Course saveCourse(@RequestBody Course course) throws  BadEntityRequest{
        System.out.println("Called saveCourse(). Adding the following new course: " + course.toString() );
        return courseService.saveCourse(course);
    }
    @PatchMapping(value = "updateCourse")
    public Course updateCourseById(@RequestBody Course course, @RequestParam Long id) throws BadEntityRequest {
        System.out.println("Updating the following course: " + course + " with id: " + id);
        return courseService.updateCourseById(course,id);
    }
    @DeleteMapping(value = "deleteCourse")
    public  void deleteCourse(Long id) throws BadEntityRequest {
        System.out.println("Called deleteCourse(). Deleting Country with Id:" + id);
        courseService.deleteCourse(id);
    }



}
