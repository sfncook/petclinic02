package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Appointment;
import com.sfncook.petclinic02.models.Vet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByVet(Vet vet);

    @Query("SELECT a FROM Appointment a WHERE :time BETWEEN start_time AND end_time AND VET_ID=:vet_id")
    public List<Appointment> findApptForVetAndTime(@Param("vet_id") Long vet_id, @Param("time") java.util.Date time);
}
