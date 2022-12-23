package com.advanced.java.database.homework.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "instructor")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Instructor {

    public Instructor(String firstName, String lastName, String college, String employeeID, Long creditsTaught){
        this.college = college;
        this.creditsTaught = creditsTaught;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
    }
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String college;
    private String employeeID;
    private Long creditsTaught;
}
