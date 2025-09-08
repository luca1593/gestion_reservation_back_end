package com.detech.gsrt.validator;

import com.detech.gsrt.dto.ReservationDto;

import java.util.ArrayList;
import java.util.List;

public class ReservationValidator {

    public static List<String> validate(ReservationDto dto) {
        List<String> errors = new ArrayList<>();

        if(dto == null) {
            errors.add("Veuillez renseigner la date du réservation");
            errors.add("Veuillez renseigner l'heure du réservation");
            errors.add("Veuillez renseigner le nombre de personne pour la réservation");
            return errors;
        }


        return errors;
    }

}
