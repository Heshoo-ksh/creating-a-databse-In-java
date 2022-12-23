package com.advanced.java.database.homework;

import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.exceptions.BadEntityRequest;
import com.advanced.java.database.homework.services.CourseService;
import com.advanced.java.database.homework.services.DatabaseService;
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
public class CourseServiceTest {
    CourseService courseService;

    @Mock
    DatabaseService databaseService;

    private final List<Course> expected = new ArrayList<>();

    @BeforeEach
    void setUp(){
        courseService = new CourseService(databaseService);
        Course courseOne = new Course(3,"Java","CIS-170","01","Richard Morgen");
        Course courseTwo = new Course(5,"Advanced Java","CIS-271","01","Richard Morgen");
        courseOne.setId(1L);
        courseTwo.setId(2L);
        expected.add(courseOne);
        expected.add(courseTwo);
    }

    @Test
    void getAllCourses_ReturnsAllCoursesINTheDataBase(){
        when(databaseService.getAllCourses()).thenReturn(expected);
        List <Course> actual = courseService.getAllCourses();

        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void getCourseByID_ReturnsCourseById() throws BadEntityRequest {
        Optional<Course>  returnedCourse = Optional.of(expected.get(0));
        Optional<Course>  expectedCourse = Optional.of(expected.get(0));

        when(databaseService.getCourseById(1L)).thenReturn(returnedCourse);

        Optional<Course> actualCourse = courseService.getCourseById(2L);
        assertThat(returnedCourse,is(equalTo(actualCourse)));
    }
    @Test
    void addCourse_addsCourseToTheDataBase() throws BadEntityRequest {
        Course returnedCourse = (expected.get(0));

        when(databaseService.saveCourse(expected.get(0))).thenReturn(returnedCourse);

        Course expectedCourse =expected.get(0);
        Course actualCourse = courseService.saveCourse(expected.get(0));

        assertThat(expectedCourse,is(equalTo(actualCourse)));
    }
    @Test
    void deleteCourse_deletesACourseByID() throws BadEntityRequest {
        Course returnedCourse = (expected.get(0));

        when(databaseService.saveCourse(expected.get(0))).thenReturn(returnedCourse);
        when(databaseService.getCourseById(2L)).thenReturn(Optional.of(returnedCourse));

        courseService.deleteCourse(1L);
        Mockito.verify(databaseService).deleteCourse(1L);
    }
    @Test
    void update_Course_updatesTheCourseInTheDataBase() throws BadEntityRequest {
        Course returnedCourse = (expected.get(0));
        Course expectedCourse =expected.get(0);

        //when(databaseService.getCourseById(1L)).thenReturn(Optional.of(returnedCourse));
        when(databaseService.saveCourse(expected.get(0))).thenReturn(returnedCourse);

        Course actualCourse = courseService.updateCourseById(expected.get(0),expected.get(0).getId());

        assertThat(returnedCourse,is(equalTo(actualCourse)));
    }
}
