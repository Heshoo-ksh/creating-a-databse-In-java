package com.advanced.java.database.homework.controllers;

import com.advanced.java.database.homework.entities.Instructor;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }
    @GetMapping (value = "getAllInstructors")
    public List<Instructor> getAllInstructors(){
        System.out.println("Called getAllInstructors()");
        return instructorService.getAllInstructors();
    }
    @GetMapping(value = "getInstructorById")
    public Optional<Instructor> getInstructorById(Long id){
        System.out.println("Called getInstructorById() with id" + id);
        return instructorService.getInstructorById(id);
    }
    @PostMapping(value = "saveInstructor")
    public Instructor saveInstructor(@RequestBody Instructor instructor) throws BadEntityRequest {
        System.out.println("Called saveInstructor(). Adding the following new instructor: " + instructor.toString() );
        return instructorService.saveInstructor(instructor);
    }
    @PatchMapping(value = "updateInstructor")
    public  Instructor updateInstructor(@RequestBody Instructor instructor, @RequestParam Long id) throws  BadEntityRequest{
        System.out.println("Updating the following course: " + instructor + " with id: " + id);
        return instructorService.updateInstructor(instructor, id);
    }
    @DeleteMapping(value = "deleteInstructor")
    public void deleteInstructor(@RequestParam Long id) throws BadEntityRequest
    {
        System.out.println("Called deleteCourse(). Deleting Country with Id:" + id);
        instructorService.deleteInstructor(id);
    }

}
