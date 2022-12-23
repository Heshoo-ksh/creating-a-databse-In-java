package com.advanced.java.database.homework.services;

import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final DatabaseService databaseService;

    @Autowired
    public CourseService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public List<Course> getAllCourses() {
        return databaseService.getAllCourses();
    }

    public Optional<Course> getCourseById(Long id) throws BadEntityRequest {
        if (databaseService.getCourseById(id).isEmpty())
            throw new BadEntityRequest("Course with the id "  + id +" does not exist");
        return databaseService.getCourseById(id);
    }

    public Course saveCourse(Course course) throws  BadEntityRequest {

        //add if statements for validation
        if (course.getCourseName().isEmpty() || course.getCourseName().isBlank())
            throw  new BadEntityRequest("The Course name is required.");
        if (course.getCourseNumber() < 1 || course.getCourseNumber() == null)
            throw new BadEntityRequest("Course Number is required and must be valid (greater than zero)");
        if (course.getTypeAbbreviation().isEmpty() || course.getTypeAbbreviation().isBlank())
            throw new BadEntityRequest("Course abbreviation is required.");
        if (course.getSection().isBlank() || course.getSection().isEmpty())
            throw new BadEntityRequest("Course Section is required");
        if (course.getInstructor().isEmpty() || course.getInstructor().isBlank())
            throw new BadEntityRequest("Instructor is required");
        return databaseService.saveCourse(course);
    }

    public Course updateCourseById(Course course, Long id) throws  BadEntityRequest {

        //add if statements for validation
        if (course.getCourseName().isEmpty() || course.getCourseName().isBlank())
            throw  new BadEntityRequest("The Course name is required.");
        if (course.getCourseNumber() < 1 || course.getCourseNumber() == null)
            throw new BadEntityRequest("Course Number is required and must be valid (greater than zero)");
        if (course.getTypeAbbreviation().isEmpty() || course.getTypeAbbreviation().isBlank())
            throw new BadEntityRequest("Course abbreviation is required.");
        if (course.getSection().isBlank() || course.getSection().isEmpty())
            throw new BadEntityRequest("Course Section is required");
        if (course.getInstructor().isEmpty() || course.getInstructor().isBlank())
            throw new BadEntityRequest("Instructor is required");
        course.setId(id);
        return databaseService.saveCourse(course);
    }
    public void deleteCourse(Long id) throws BadEntityRequest {

        if (databaseService.getCourseById(id).isEmpty())
            throw new BadEntityRequest("The course with id " + id + " doesn't exist.  Delete failed.");
        databaseService.deleteCourse(id);
    }
}
