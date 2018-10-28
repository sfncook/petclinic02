package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Owner;
import com.sfncook.petclinic02.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByPets(Pet pet);
}
