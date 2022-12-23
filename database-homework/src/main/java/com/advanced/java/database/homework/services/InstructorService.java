package com.advanced.java.database.homework.services;

import com.advanced.java.database.homework.entities.Instructor;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final DatabaseService databaseService;

    @Autowired
    public InstructorService(DatabaseService databaseService){ this.databaseService = databaseService;}

    public List<Instructor> getAllInstructors() {
        return databaseService.getAllInstructors();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return databaseService.getInstructorById(id);
    }

    public Instructor saveInstructor(Instructor instructor) throws BadEntityRequest {

        if (instructor.getFirstName().isBlank() || instructor.getFirstName().isEmpty())
            throw new BadEntityRequest( "Instructor first name is a required field.");
        if (instructor.getLastName().isBlank() || instructor.getLastName().isEmpty())
            throw new BadEntityRequest("Instructor Last name is a required field.");
        if (instructor.getEmployeeID().isEmpty() || instructor.getEmployeeID().isBlank())
            throw new BadEntityRequest("Instructor ID number is a required field.");
        if (instructor.getCollege().isBlank()|| instructor.getCollege().isEmpty())
            throw new BadEntityRequest("Instructor college is a required field.");
        if (instructor.getCreditsTaught() < 0 || instructor.getCreditsTaught() == null)
            throw  new BadEntityRequest("Instructor Credits thought is a required field, and must valid");

        return databaseService.saveInstructor(instructor);
    }

    public Instructor updateInstructor(Instructor instructor, Long id) throws  BadEntityRequest {
        if (instructor.getFirstName().isBlank() || instructor.getFirstName().isEmpty())
            throw new BadEntityRequest( "Instructor first name is a required field.");
        if (instructor.getLastName().isBlank() || instructor.getLastName().isEmpty())
            throw new BadEntityRequest("Instructor Last name is a required field.");
        if (instructor.getEmployeeID().isEmpty() || instructor.getEmployeeID().isBlank())
            throw new BadEntityRequest("Instructor ID number is a required field.");
        if (instructor.getCollege().isBlank()|| instructor.getCollege().isEmpty())
            throw new BadEntityRequest("Instructor college is a required field.");
        if (instructor.getCreditsTaught() < 0 || instructor.getCreditsTaught() == null)
            throw  new BadEntityRequest("Instructor Credits thought is a required field, and must valid");
        instructor.setId(id);
        return databaseService.saveInstructor(instructor);
    }

    public void deleteInstructor(Long id) throws  BadEntityRequest{
        if ( databaseService.getInstructorById(id).isEmpty()){
            throw new BadEntityRequest("The instructor with id " + id + " doesn't exist.  Delete failed.");
        }
        databaseService.deleteInstructor(id);
    }
}
