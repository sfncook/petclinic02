package com.sfncook.petclinic02.components;

import com.sfncook.petclinic02.models.Appointment;
import com.sfncook.petclinic02.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AppointmentValidator {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentValidator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public boolean isApptTimeValid(Appointment appointment) throws AppointmentValidationException {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(appointment.getStartTime());

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(appointment.getEndTime());

        // Are start and end times on the same day?
        if( !
                (startCalendar.get(Calendar.DAY_OF_YEAR)==endCalendar.get(Calendar.DAY_OF_YEAR)) &&
                (startCalendar.get(Calendar.YEAR)==endCalendar.get(Calendar.YEAR))
        ) {
            throw new AppointmentValidationException("Appointment times are invalid: Start and End times must be on the same day.");
        }

        // Is appt day M-F?
        if( !
                (startCalendar.get(Calendar.DAY_OF_WEEK)>=Calendar.MONDAY) &&
                (startCalendar.get(Calendar.DAY_OF_WEEK)<=Calendar.FRIDAY)
        ) {
            throw new AppointmentValidationException("Appointment time is invalid: Vet is only open Monday-Friday.");
        }

        // Is start time between 8a-5p?
        if( !
                (startCalendar.get(Calendar.HOUR_OF_DAY)>=8) &&
                (startCalendar.get(Calendar.HOUR_OF_DAY)<=17)
        ) {
            throw new AppointmentValidationException("Appointment START time is invalid: Vet is only open between 8:00am and 5:00pm.");
        }

        // Is end time between 8a-5p?
        if( !
                (endCalendar.get(Calendar.HOUR_OF_DAY)>=8) &&
                (endCalendar.get(Calendar.HOUR_OF_DAY)<=17)
        ) {
            throw new AppointmentValidationException("Appointment END time is invalid: Vet is only open between 8:00am and 5:00pm.");
        }

        // Is start time < end time?
        if( !
                appointment.getStartTime().before(appointment.getEndTime())
        ) {
            throw new AppointmentValidationException("Appointment times are invalid: Start time should be BEFORE end time");
        }

        // Does time conflict with other appointments?
//        List<Appointment> apptForVetAndTime = appointmentRepository.findApptForVetAndTime(appointment.getVet().getId(), appointment.getStartTime(), appointment.getEndTime());
        Iterable<Appointment> allAppts = appointmentRepository.findAll();
        for(Appointment otherAppt : allAppts) {
            if(
                !otherAppt.getId().equals(appointment.getId()) &&
                otherAppt.getVet().getId().equals(appointment.getVet().getId()) &&
                otherAppt.getStartTime().before(appointment.getEndTime()) &&
                otherAppt.getEndTime().after(appointment.getStartTime())
            ) {
                throw new AppointmentValidationException("Appointment conflicts with other appointment.");
            }
        }

        return true;
    }

    @AllArgsConstructor
    public static final class AppointmentValidationException extends Exception {
        @Getter
        private final String errorMessage;
    }
}
