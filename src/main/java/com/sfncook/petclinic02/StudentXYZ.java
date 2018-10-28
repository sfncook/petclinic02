package com.sfncook.petclinic02;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentXYZ {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passportNumber;
}