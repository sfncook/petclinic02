package com.sfncook.petclinic02.controllers;

import com.sfncook.petclinic02.components.AppointmentValidator;
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
    private AppointmentValidator apptValidator;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentValidator apptValidator) {
        this.appointmentRepository = appointmentRepository;
        this.apptValidator = apptValidator;
    }

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<List<Appointment>> getAll() {
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment appointment : appointmentRepository.findAll()) {
            appointments.add(appointment);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public ResponseEntity create(@RequestBody Appointment appointment) {
        List<Appointment> apptForVetAndTime = appointmentRepository.findApptForVetAndTime(appointment.getVet().getId(), appointment.getStartTime(), appointment.getEndTime());
        if(!apptForVetAndTime.isEmpty()) {
            return new ResponseEntity<>("Appointment conflicts with other appointments for that vet.", HttpStatus.BAD_REQUEST);
        }

        appointment.setId(null); // Necessary so repo doesn't think we're trying to *update* an obj
        Appointment rsp = appointmentRepository.save(appointment);
        return new ResponseEntity<>(rsp, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public ResponseEntity update(@RequestBody Appointment appointment) {
        try {
            if (apptValidator.isApptTimeValid(appointment.getStartTime(), appointment.getEndTime())) {
                Appointment rsp = appointmentRepository.save(appointment);
                return new ResponseEntity<>(rsp, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Appointment time is invalid.  Vet is only open M-F 8a-5pm.", HttpStatus.BAD_REQUEST);
            }
        } catch(AppointmentValidator.AppointmentValidationException e) {
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(value = {"","/"}, method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity delete(@RequestBody Appointment appointment) {
        appointmentRepository.delete(appointment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = {"{id:[\\d]+}","/{id:[\\d]+}"}, method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        appointmentRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
