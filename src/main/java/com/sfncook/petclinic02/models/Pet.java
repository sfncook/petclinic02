package com.sfncook.petclinic02.models;

import javax.persistence.*;

@Entity
public class Pet {

    static enum Species {
        DOG,
        CAT,
        BIRD
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Species species;
}
