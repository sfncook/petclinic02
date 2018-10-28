package com.sfncook.petclinic02.repositories;

import com.sfncook.petclinic02.models.Appointment;
import com.sfncook.petclinic02.models.Vet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
    
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByVet(Vet vet);

    @Query("SELECT a FROM Appointment a WHERE vet_id=:vet_id AND (\n" +
            ":start_time BETWEEN start_time AND end_time OR \n" +
            ":end_time BETWEEN start_time AND end_time OR \n" +
            "(:start_time <= start_time AND :end_time >= end_time))")
    public List<Appointment> findApptForVetAndTime(
            @Param("vet_id") Long vet_id,
            @Param("start_time") java.util.Date start_time,
            @Param("end_time") java.util.Date end_time
    );
}
