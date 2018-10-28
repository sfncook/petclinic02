package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
