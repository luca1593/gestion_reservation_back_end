package com.detech.gsrt.validator;

import com.detech.gsrt.dto.TableDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TableValidator {

    public static List<String> validate(TableDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null) {
            errors.add("Veuillez renseigner le nom de la table");
            errors.add("La capacité de la table doit être surperieur ou égale à 1");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNom())) {
            errors.add("Veuillez renseigner le nom de la table");
        }

        if (dto.getCapacite() <= 0) {
            errors.add("La capacité de la table doit être surperieur ou égale à 1");
        }

        return errors;
    }
}
