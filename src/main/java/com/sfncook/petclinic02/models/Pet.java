package com.sfncook.petclinic02.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Species species;

    enum Species {
        DOG,
        CAT,
        BIRD
    }
}
