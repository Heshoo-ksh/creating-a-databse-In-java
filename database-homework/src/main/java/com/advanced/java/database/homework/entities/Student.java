package com.advanced.java.database.homework.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor

public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer ssn;
    private String studentID;
    private Long gpa;
    private Integer creditsEarned;
    private String major;

    public Student (String firstName,String lastName,Integer ssn, String studentID, Long gpa, Integer creditsEarned, String major){

        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn =ssn;
        this.studentID = studentID;
        this.gpa = gpa;
        this.creditsEarned = creditsEarned;
        this.major = major;
        this.id =id;
    }
}
