package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
