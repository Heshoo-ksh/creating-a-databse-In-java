package com.advanced.java.database.homework;

import com.advanced.java.database.homework.entities.Course;
import com.advanced.java.database.homework.entities.Instructor;
import com.advanced.java.database.homework.entities.Student;
import com.advanced.java.database.homework.repositories.CourseRepository;
import com.advanced.java.database.homework.repositories.InstructorRepository;
import com.advanced.java.database.homework.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runStudent (StudentRepository studentRepository) throws Exception {
		return (String[] args) -> {
			Student student1 = new Student("Hisham","Odeh",311313131,"43", (long) 3.5,67,"ComputerScience");
			studentRepository.save(student1);
			studentRepository.findAll().forEach(System.out::println);
		};
	}
	@Bean
	public CommandLineRunner runCourse (CourseRepository courseRepository) throws Exception{
		return (String[] args) -> {
			Course course1 = new Course(3,"Java","CIS-271","01","Richard Morgen");
			courseRepository.save(course1);
			courseRepository.findAll().forEach(System.out::println);
		};
	}
	@Bean
	public CommandLineRunner runInstructor(InstructorRepository instructorRepository) throws Exception{
		return (String[] args) -> {
			Instructor instructor = new Instructor("Richard","Morgen","HFC", "in324342", 56L);
			instructorRepository.save(instructor);
			instructorRepository.findAll().forEach(System.out::println);
		};

	}
}
