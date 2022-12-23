package com.advanced.java.database.homework.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "course")
@Setter
@Getter
@NoArgsConstructor
public class Course {


    public Course (Integer courseNumber, String courseName,String typeAbbreviation,String section,String instructor){
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.typeAbbreviation =typeAbbreviation;
        this.section =section;
        this.instructor = instructor;
    }
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private Integer courseNumber;
    private String courseName;
    private String typeAbbreviation;
    private String section;
    private String instructor;

}
