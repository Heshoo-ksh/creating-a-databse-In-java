package com.advanced.java.database.homework;

import com.advanced.java.database.homework.entities.Instructor;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.DatabaseService;
import com.advanced.java.database.homework.services.InstructorService;
import net.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@SpringBootTest
public class InstructorServiceTest {

    InstructorService instructorService;

    @Mock
    DatabaseService databaseService;


    private final List<Instructor> expected = new ArrayList<Instructor>();

    @BeforeEach
    void setup(){
        instructorService = new InstructorService(databaseService);
        Instructor instructor = new Instructor("Richard","Morgen","HFC", "in324342", 56L);
        instructor.setId(1L);
        expected.add(instructor);
    }

    @Test
    void getAllInstructors_ReturnsAllInstructorsInTheDataBase(){
        when(databaseService.getAllInstructors()).thenReturn( expected);
        List <Instructor> actual =  instructorService.getAllInstructors();

        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void getInstructorById_ValidIdSentIn_ReturnsSelectedInstructor() throws BadEntityRequest {
        Optional<Instructor> returnedInstructor = Optional.of(expected.get(0));
        Long id = 1L;

        when(databaseService.getInstructorById(id)).thenReturn( returnedInstructor);

        Optional<Instructor> expectedInstructor = Optional.of(expected.get(0));
        Optional<Instructor> actualInstructor = instructorService.getInstructorById(1L);

        assertThat(expectedInstructor,is(equalTo(actualInstructor)));
    }
    @Test
    void addInstructor_addInstructorToTheDataBase() throws BadEntityRequest {
        Instructor returnedInstructor = (expected.get(0));

        when(databaseService.saveInstructor(expected.get(0))).thenReturn(returnedInstructor);

        Instructor expectedInstructor =expected.get(0);
        Instructor actualInstructor = instructorService.saveInstructor(expected.get(0));

        assertThat(expectedInstructor,is(equalTo(actualInstructor)));
    }

    @Test
    void deleteInstructor_deletesAInstructorByID() throws BadEntityRequest {
        Instructor returnedInstructor = (expected.get(0));

        when(databaseService.saveInstructor(expected.get(0))).thenReturn(returnedInstructor);
        when(databaseService.getInstructorById(1L)).thenReturn(Optional.of(returnedInstructor));

        instructorService.deleteInstructor(1L);
        Mockito.verify(databaseService).deleteInstructor(1L);
    }
    @Test
    void update_Instructor_updatesTheInstructorInTheDataBase() throws BadEntityRequest {
        Instructor returnedInstructor = (expected.get(0));
        Instructor expectedInstructor =expected.get(0);

        when(databaseService.getInstructorById(1L)).thenReturn(Optional.of(returnedInstructor));
        when(databaseService.saveInstructor(expected.get(0))).thenReturn(returnedInstructor);

        Instructor actualInstructor = instructorService.updateInstructor(expected.get(0),expected.get(0).getId());

        assertThat(expectedInstructor,is(equalTo(actualInstructor)));
    }

}
