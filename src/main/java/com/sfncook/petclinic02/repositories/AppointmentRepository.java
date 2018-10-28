package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Appointment;
import com.sfncook.petclinic02.models.Vet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByVet(Vet vet);

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<Person> find(@Param("lastName") String lastName);
}
