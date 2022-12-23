package com.advanced.java.database.homework.repositories;

import com.advanced.java.database.homework.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository <Student, Long> {
}
