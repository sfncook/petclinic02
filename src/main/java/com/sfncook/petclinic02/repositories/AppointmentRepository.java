package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
