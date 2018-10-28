package com.sfncook.petclinic02.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private java.util.Date utilTime;
}
