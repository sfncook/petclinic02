package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
