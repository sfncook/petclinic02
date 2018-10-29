package com.sfncook.petclinic02.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "America/Los_Angeles")
    private Date startTime;

    @Getter
    @Setter
    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "America/Los_Angeles")
    private Date endTime;

    @ManyToOne
    @Getter
    @Setter
    private Pet pet;

    @ManyToOne
    @Getter
    @Setter
    private Vet vet;
}
