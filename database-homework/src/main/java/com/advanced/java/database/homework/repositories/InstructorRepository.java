package com.advanced.java.database.homework.repositories;

import com.advanced.java.database.homework.entities.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
