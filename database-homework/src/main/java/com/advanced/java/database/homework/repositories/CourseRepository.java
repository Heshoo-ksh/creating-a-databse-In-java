package com.advanced.java.database.homework.repositories;

import com.advanced.java.database.homework.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
