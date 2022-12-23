package com.advanced.java.database.homework;

import com.advanced.java.database.homework.entities.Student;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.DatabaseService;
import com.advanced.java.database.homework.services.StudentService;
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
public class
StudentServiceTest {

    StudentService studentService;

    @Mock
    DatabaseService databaseService;

    private final List<Student> expected = new ArrayList<Student>();

    @BeforeEach
    void setup(){
        studentService = new StudentService(databaseService);
        Student studentOne = new Student("Hisham","Odeh",311313131,"43", (long) 3.5,67,"ComputerScience");
        Student studentTwo = new Student("Abdallah","Char",43565465,"43", (long) 4.0,67,"Bissnius");
        studentOne.setId(1L);
        studentTwo.setId(2L);
        expected.add(studentOne);
        expected.add(studentTwo);
    }

    @Test
    void getAllStudents_ReturnsAllStudentsInTheDataBase(){
        when(databaseService.getAllStudents()).thenReturn( expected);
        List <Student> actual =  studentService.getAllStudents();

        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void getStudentById_ValidIdSentIn_ReturnsSelectedStudent() throws BadEntityRequest {
        Optional<Student> returnedStudent = Optional.of(expected.get(0));
        Long id = 1L;

        when(databaseService.getStudentById(id)).thenReturn( returnedStudent);

        Optional<Student> expectedStudent = Optional.of(expected.get(0));
        Optional<Student> actualStudent = studentService.getStudentById(1L);

        assertThat(expectedStudent,is(equalTo(actualStudent)));
    }
    @Test
    void addStudent_addsStudentToTheDataBase() throws BadEntityRequest {
        Student returnedStudent = (expected.get(0));

        when(databaseService.saveStudent(expected.get(0))).thenReturn(returnedStudent);

        Student expectedStudent =expected.get(0);
        Student actualStudent = studentService.addStudent(expected.get(0));

        assertThat(expectedStudent,is(equalTo(actualStudent)));
    }

    @Test
    void deleteStudent_deletesAStudentByID() throws BadEntityRequest {
        Student returnedStudent = (expected.get(0));

        when(databaseService.saveStudent(expected.get(0))).thenReturn(returnedStudent);
        when(databaseService.getStudentById(1L)).thenReturn(Optional.of(returnedStudent));

        studentService.deleteStudent(1L);
        Mockito.verify(databaseService).deleteStudent(1L);
    }
    @Test
    void update_student_updatesTheStudentInTheDataBase() throws BadEntityRequest {
        Student returnedStudent = (expected.get(0));
        Student expectedStudent =expected.get(0);

        when(databaseService.getStudentById(1L)).thenReturn(Optional.of(returnedStudent));
        when(databaseService.saveStudent(expected.get(0))).thenReturn(returnedStudent);

        Student actualStudent = studentService.updateStudent(expected.get(0),expected.get(0).getId());

        assertThat(expectedStudent,is(equalTo(actualStudent)));
    }

}
