package com.sfncook.petclinic02.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class AppointmentValidator {
    public boolean isApptTimeValid(Date startTime, Date endTime) throws AppointmentValidationException {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startTime);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endTime);

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
                startTime.before(endTime)
        ) {
            throw new AppointmentValidationException("Appointment times are invalid: Start time should be BEFORE end time");
        }
        return true;
    }

    @AllArgsConstructor
    public static final class AppointmentValidationException extends Exception {
        @Getter
        private final String errorMessage;
    }
}
