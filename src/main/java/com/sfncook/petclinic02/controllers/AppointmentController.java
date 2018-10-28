package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.models.Appointment;
import com.sfncook.petclinic02.models.Vet;
import com.sfncook.petclinic02.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/appointments","/appointments/"})
public class AppointmentController {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Appointment>> getAll() {
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment appointment : appointmentRepository.findAll()) {
            appointments.add(appointment);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity create(@RequestBody Appointment appointment) {
        List<Appointment> apptForVetAndTime = appointmentRepository.findApptForVetAndTime(appointment.getVet().getId(), appointment.getStartTime(), appointment.getEndTime());
        if(!apptForVetAndTime.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        appointment.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Appointment rsp = appointmentRepository.save(appointment);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) {
        Appointment rsp = appointmentRepository.save(appointment);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity delete(@RequestBody Appointment appointment) {
        appointmentRepository.delete(appointment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"{id:[\\d]+}","/{id:[\\d]+}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        appointmentRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
